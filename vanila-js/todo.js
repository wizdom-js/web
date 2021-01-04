const toDoForm = document.querySelector(".js-toDoForm"),
  toDoInput = toDoForm.querySelector("input"),
  toDoList = document.querySelector(".js-toDoList");

const TODOS_LS = "toDos";

function paintToDo(text) {
  // 비어있는 li 엘리먼트 생성 그담 버튼, span 차례대로
  const li = document.createElement("li");
  const delBtn = document.createElement("button");
  delBtn.innerHTML = "❌";
  const span = document.createElement("span");
  // submit function에서 온 text
  span.innerText = text;
  // 버튼 -> span 순으로 li에 넣기
  li.appendChild(delBtn);
  li.appendChild(span);
  // 금방 만든 li 넣기
  toDoList.appendChild(li);
}

function handleSubmit(event) {
  // 엔터 누르면 새로고침 막자
  event.preventDefault();
  const currentValue = toDoInput.value;
  // 누군가 엔터 눌렀을때 todo생성하고 input 안은 삭제하기 (submit처럼)
  paintToDo(currentValue);
  toDoInput.value = "";
}

function loadToDos() {
  const toDos = localStorage.getItem(TODOS_LS);
  // toDos가 null이 아니면
  if (toDos !== null) {
  }
}

function init() {
  loadToDos();
  // todo 생성해주기
  toDoForm.addEventListener("submit", handleSubmit);
}

init();
