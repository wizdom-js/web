const title = document.querySelector("#title");

// title을 클릭할때마다 제목 색깔을 빨간색으로 바꾸기
function handleClick() {
  title.style.color = "red";
}

//title은 click 이벤트를 기다리고 있게
title.addEventListener("click", handleClick);
