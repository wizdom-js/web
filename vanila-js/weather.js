const API_KEY = "c902eb9aee51998b30d90694ef0a29f7";
const COORDS = "coords";

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
    askForCoords();
  } else {
    // getWeather 함수 호출
  }
}

function init() {
  loadCoords();
}

init();
