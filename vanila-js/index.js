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
