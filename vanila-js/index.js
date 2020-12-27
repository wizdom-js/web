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
