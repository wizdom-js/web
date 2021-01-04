const toDoForm = document.querySelector(".js-toDoForm"),
  toDoInput = toDoForm.querySelector("input"),
  toDoList = document.querySelector(".js-toDoList");

const TODOS_LS = "toDos";

// 할 일을 여러개가 모인 목록으로 저장해야 하기 때문에 array 만들기
// 할 일을 저장하면 이 toDos array에 저장되게
let toDos = [];

// todo 지우기
function deleteToDo(event) {
  // 어떤 버튼인지 알기 위함. 부모(어떤 li인지)가 누구인지
  const btn = event.target;
  const li = btn.parentNode;
  // 지워준 다.
  toDoList.removeChild(li);
  const cleanToDos = toDos.filter(function (toDo) {
    // 모든 toDos가 li의 id와 같지 않을때
    // li id는 string이었으므로 int로 바꾸어준다.
    return toDo.id !== parseInt(li.id);
  });
  // toDos 교체
  toDos = cleanToDos;
  saveToDos();
}

// toDos array를 가져와서 로컬에 저장하는 일을 한다.
function saveToDos() {
  // string으로 바꿔서 local storage에 저장하기
  localStorage.setItem(TODOS_LS, JSON.stringify(toDos));
}

function paintToDo(text) {
  // 비어있는 li 엘리먼트 생성 그담 버튼, span 차례대로
  const li = document.createElement("li");
  const delBtn = document.createElement("button");
  delBtn.innerHTML = "❌";
  delBtn.addEventListener("click", deleteToDo);
  const span = document.createElement("span");
  // id 만들기 : array 길이 (몇개 투두 들어있는지)를 사용
  const newId = toDos.length + 1;
  // submit function에서 온 text
  span.innerText = text;
  // 버튼 -> span 순으로 li에 넣기
  li.appendChild(delBtn);
  li.appendChild(span);
  // li에도 id 부여 왜 ?? -> 나중에 버튼 클릭했을때 어떤 li를 지워야하는지 알 수 있기 위해
  li.id = newId;
  // 금방 만든 li 넣기
  toDoList.appendChild(li);
  const toDoObj = {
    // text라는 key에 뒤에 text가 value가 올 것 이고
    text: text,
    id: newId,
  };
  // push를 통해 array 안에 element 넣기
  toDos.push(toDoObj);
  //push 한 후에 호출해야한다. 이거 먼저 불러도 저장할게 없음 비어있음.
  saveToDos();
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
  const loadedToDos = localStorage.getItem(TODOS_LS);
  // toDos가 null이 아니면
  if (loadedToDos !== null) {
    // JS가 데이터 다룰 수 있도록 object로 바꾸기
    const parsedToDos = JSON.parse(loadedToDos);
    //parsedToDos를 화면에 보여주기. local strage에서 불러온걸 화면에 써주기
    //parsedToDos 안에 있는 것들 각각에 대해 paintToDo 함수 실행
    parsedToDos.forEach(function (toDo) {
      paintToDo(toDo.text);
    });
  }
}

function init() {
  loadToDos();
  // todo 생성해주기
  toDoForm.addEventListener("submit", handleSubmit);
}

init();
