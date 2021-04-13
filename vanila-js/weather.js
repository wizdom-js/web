const weather = document.querySelector(".js-weather");

const API_KEY = "c902eb9aee51998b30d90694ef0a29f7";
const COORDS = "coords";

// 날씨 데이터 가져오기
function getWeather(lat, lng) {
  fetch(
    `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lng}&appid=${API_KEY}&units=metric`
  ) //then 함수 : fetch가 완료 된 후 다음 함수 실행
    .then(function (response) {
      return response.json();
    })
    .then(function (json) {
      const temperature = json.main.temp;
      const place = json.name;
      weather.innerText = `현재 ${place}의 기온은 ${temperature}° 입니다.`;
    });
}

// 좌표저장
function saveCoords(coordsObj) {
  // 저장할때 string으로 바꾸기
  localStorage.setItem(COORDS, JSON.stringify(coordsObj));
}

// 좌표 가져오는거 성공했을때 함수
function handleGeoSuccess(position) {
  const latitude = position.coords.latitude;
  const longitude = position.coords.longitude;
  // 객체 저장
  const coordsObj = {
    latitude,
    longitude,
  };
  saveCoords(coordsObj);
  getWeather(latitude, longitude);
}

// 좌표 가져오는거 실패했을때 함수
function handleGeoError() {
  console.log("Cant aceess geo location");
}

// 좌표 요청하는 함수
function askForCoords() {
  navigator.geolocation.getCurrentPosition(handleGeoSuccess, handleGeoError);
}

function loadCoords() {
  const loadedCoords = localStorage.getItem(COORDS);
  if (loadedCoords === null) {
    // 결국 getWeather 함수 실행된다. local stroage 아무것도 없으면
    // askForCoords 함수가 실행되고, 이 함수 안에서 정상적으로 위치 정보 가져오게 되면
    // handle GeoSuccess가 실행되는데, 이 안에서 API가 최종적으로 호출되기 떄문.
    askForCoords();
  } else {
    // getWeather 함수 호출
    // loadedCoords 문자이니 object로 바꿔준다.
    const parsedCoords = JSON.parse(loadedCoords);
    getWeather(parsedCoords.latitude, parsedCoords.longitude);
  }
}

function init() {
  loadCoords();
}

init();
