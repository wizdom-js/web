const body = document.querySelector("body");

// 이미지 몇개가지고 있는지
const IMG_NUMBER = 4;

function paintImage(imgNumber) {
  const image = new Image();
  image.src = `images/${imgNumber + 1}.jpg`;
  // 생성된 이미지에 class이름 주기
  image.classList.add("bgImage");
  body.prepend(image);
}

function genRandom() {
  // 0 - img_number 이내의 숫자 랜덤으로 생성 (소수점 버리기)
  const number = Math.floor(Math.random() * IMG_NUMBER);
  return number;
}

function init() {
  // 숫자를 생성하게 하기
  const randomNumber = genRandom();
  // 이미지 나타나게
  paintImage(randomNumber);
}

init();
