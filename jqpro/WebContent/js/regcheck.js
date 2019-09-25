/**
 * 
 */

idcheck = function(idvalue) {
   // 입력값.match(정규식) - 일치하면 일치되는 값을 없으면 null을 리턴
   // 정규식.test(입력값) - 일치하면 true를 리턴 않으면 false를 리턴
   // 1.공백체크 // 2.길이체크  // 3.정규식

   if (idvalue.trim().length == 0) {
      alert("아이디를 입력해주세요.");
      return false;
   }

//   var whiteSpace = "/\s/g";
   if (idvalue.trim().length != idvalue.length) {
      alert("id는 공백을 포함할 수 없습니다.");
      return false;
   } else if (idvalue.length < 4 || idvalue.length > 12) {
      alert("id는 4~12사이입니다.");
      return false;
   };

   var pattern = /^[a-z][a-zA-Z0-9]{3,12}$/;

   if (!pattern.test(idvalue)) {
      alert('id는 영문 소문자로 시작하고 두번째 글짜부터는 영문자 대소문자, 숫자를 조합한 4~12자리로 입력하세요.');
      return false;
   }

   return true;
}

datacheck = function() {
   // pass,name,hp,mail - 공백검증과 길이검증,정규식체크

   name = $('#name').val();
   pass = $('#psw').val();
   hp = $('#ptel').val();
   mail = $('#email').val();

   
   //이름
   if (name.trim().length == 0) {
      alert("이름을 입력해주세요.");
      return false;
   }

   var regKor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{1,5}$/;
   var regEng = /^[a-zA-Z]{4,10}$/;

   if (name.trim().length != name.length) {
      alert("이름은 공백을 포함할 수 없습니다.");
      return false;
   }else if(name.match(regKor) || name.match(regEng)) {
      if (regKor.test(name)) { //한글만 있는지
         if (!(name.trim().length >= 2 && name.trim().length <= 5)) {
            alert("한글이름은 2~5사이만 가능합니다.");
            return false;
         }
      }else if (regEng.test(name)) {
         if (!(name.trim().length >= 5 && name.trim().length <= 10)) {
            alert("영어이름은 5~10사이만 가능합니다.");
            return false;
         }
      }
   } else {
      alert('이름은 한글/영어만 가능합니다.(단, 혼용은 할 수 없습니다.)');
      return false;
   }

   //비밀번호
   if (pass.trim().length == 0) {
      alert("비밀번호를 입력해주세요.");
      return false;
   }

   var word = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{3,10}/;
   if (!word.test(pass)) {
      alert('영문소문자 대문자 숫자 특수문자가 반드시 한글자 이상이 포함되야하면 4~10글자여야 합니다.');
      return false;
   }
   //비밀번호end
   
   //hp
   //1.공백체크    //2.정규식체크
   var regHP = /(01[016789])([1-9]{1}[0-9]{1,3})([0-9]{4})/;
   if (hp.trim().length == 0) {
      alert("공백입니다.핸드폰번호를 입력해주세요.");
      return false;
   }

   if (hp.trim().length != 11) {
      alert("휴대폰은  11자리입니다.");
      return;
   }

   if (!(regHP.test(hp))) {
      alert('휴대폰 번호 양식에 맞게 입력하되 숫자만 입력해주세요.');
      return false;
   }
   //hp end
   

   //mail
   //1.공백   //2.길이   //3.정규식
    var regMail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    if(mail.trim().length==0){
       alert("이메일을 입력해주세요");
       return false;
    }
       
    if(!(regMail.test(mail))){
       alert('이메일 양식에 맞게 작성해주세요.');
       return false;
    }
    
    return true;
}