const clockContainer = document.querySelector(".js-clock"),
  //  document에서 찾아 보는게 아니라 js-clock의 자식 중에 찾는다.
  clockTitle = clockContainer.querySelector("h1");

// 얻은 시간을 가지고 시계 부분 html 변경시킴
function getTime() {
  const date = new Date();
  const minutes = date.getMinutes();
  const hours = date.getHours();
  const seconds = date.getSeconds();
  clockTitle.innerText = `${hours < 10 ? `0${hours}` : hours}:${
    minutes < 10 ? `0${minutes}` : minutes
  }:${seconds < 10 ? `0${seconds}` : seconds}`;
}

function init() {
  getTime();
  // 시간 얻기 매초마다
  setInterval(getTime, 1000);
}
init();
