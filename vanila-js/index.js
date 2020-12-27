const title = document.querySelector("#title");

// 색깔값 지정해주기 (대문자로 선언 많이 함)
const BASE_COLOR = "rgb(52, 73, 94)";
const OTHER_COLOR = "#7f8c8d";

function handleClick() {
  const currentColor = title.style.color;
  // 만약 현재 색깔이 기본색과 같다면 ~
  // 이 조건문은 처음엔 무조건 참이다. 밑의 init에서 설정해줬으니까
  // 근데 또 클릭하면 csurrentCOlor는 BASE_COLOR와 동일하지 않게됨
  if (currentColor === BASE_COLOR) {
    title.style.color = OTHER_COLOR;
  } else {
    title.style.color = BASE_COLOR;
  }
}

// 어플리케이션을 초기화하기
function init() {
  title.style.color = BASE_COLOR;
  title.addEventListener("click", handleClick);
}
// 함수 호출
init();

// 오프라인일때 출력
function handleOffline() {
  console.log("no wifi");
}
// 온라인일때 출력
function handleOnline() {
  console.log("Welcome back");
}

window.addEventListener("offline", handleOffline);
window.addEventListener("online", handleOnline);
