const title = document.querySelector("#title");

// 클래스 이름 지정해주기
const CLICKED_CLASS = "clicked";

function handleClick() {
  // value값 가져오기
  // class list에 clicked있으면 true 줄거고 없으면 false 줌
  const hasClass = title.classList.contains(CLICKED_CLASS);
  if (!hasClass) {
    //CLICKED_CLASS가 클래스 리스트에 있지 않다면 같지 않다면 추가해준다.
    // 값 세팅
    title.classList.add(CLICKED_CLASS);
  } else {
    // CLICKED_CLASS 있으면 없애주기
    title.classList.remove(CLICKED_CLASS);
  }
}

// toggle로 위의 조건문식 완전 간단하게 하기
// function handleClick() {
//   title.classList.toggle(CLICKED_CLASS);
// }

// 어플리케이션을 초기화하기
// 클릭했을때 handleClick()함수 실행한다.
function init() {
  title.addEventListener("click", handleClick);
}
// 함수 호출
init();
