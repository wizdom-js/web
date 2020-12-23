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
