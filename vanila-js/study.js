// function
function sayHello(name, age) {
  console.log("Hello!", name, "You have", age, "years of age.");
}

sayHello("rudolph", 15);

console.log("HI");

// 쉼표 대신 sexy string 이용하기
// --> 백틱 ₩을 이용하기
function sayHello(name, age) {
  console.log(`Hello ${name} you are ${age} years old`);
}

sayHello("Nicolas", 15);

// returning X ? ?
function sayHello(name, age) {
  console.log(`Hello ${name} you are ${age} years old`);
}

const greetNicolas = sayHello("Nicolas", 14);
// greetNicolas는  sayHello 함수의 리턴값과 같다.
console.log(greetNicolas);
// 정의되지 않았다고 나온다. 왜냐면 아무것도 반환해주지 않았으니

// 그래서 반환해주기(returning X)
function sayHello(name, age) {
  return `Hello ${name} you are ${age} years old`;
}

// const greetNicolas = sayHello("Nicolas", 14);
// (앞에 있으니 주석처리 해주었음)이렇게 어떤 값을 반환해주었다.
// console.log로 찍어주기
console.log(greetNicolas);

// 우리만의 객체 만들기(console.log 처럼)
const calculator = {
  plus: function (a, b) {
    return a + b;
  },
  multiply: function (a, b) {
    return a * b;
  },
  divide: function (a, b) {
    return a / b;
  },
  minus: function (a, b) {
    return a - b;
  },
  power: function (a, b) {
    return a ** b;
  },
};

// calculator 는 객체(object) (console과 같은)
// .plus()는 (2개의) 인자를 가지는 함수 (log()와 같은)
const plus = calculator.plus(5, 5);
const multiply = calculator.multiply(5, 5);
const divide = calculator.divide(5, 5);
const minus = calculator.minus(5, 5);
const power = calculator.power(5, 5);

console.log(plus);
console.log(multiply);
console.log(divide);
console.log(minus);
console.log(power);

// 아이디 선택하기 및 제목 바꾸기
// document.getElementByID(""); 사용한다.
// const title = document.getElementById("title");

//querySelector 이용하기
const title = document.querySelector("#title");

// 타이틀 콘솔에 보기
console.log(title);

// 타이틀이 가진 함수 콘솔로 보기
// console.dir(title);

// 제목바꾸기
title.innerHTML = "Hi From JS";
//색 바꾸기
title.style.color = "red";

// title바꾸기
document.title = "Wow";

// event 발생할때마다 로그에 보여주기
function handleResize(event) {
  console.log(event);
}

// 이벤트 가로채기
// window resize는 handleResize를 호출하는거임!
// handleResize라고 적어야한다. 그래야 우리가 원하는 시간에 호출할 수 있음
// handleResize()라고 적으면 지금 바로 호출하라는거임.
window.addEventListener("resize", handleResize);

// title을 클릭할때마다 제목 색깔을 빨간색으로 바꾸기
function handleClick() {
  title.style.color = "red";
}

//title은 click 이벤트를 기다리고 있게
title.addEventListener("click", handleClick);

// 조건문 만들기
if (10 > 5) {
  console.log("hi");
} else {
  console.log("ho");
}

//나이를 입력받고 술 마실 수 있는 나이인지 아닌지 출력하기
// prompt 별로 안좋음.
const age = prompt("How old are you");

if (age >= 18 && age <= 21) {
  console.log("You can drink, but you shouldn't");
} else if (age > 21) {
  console.log("You can drink.");
} else {
  console.log("you can't drink.");
}

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
