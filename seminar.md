### 1차 세미나 과제 리드미

## 1. SignInActivity.kt
 - DataBinding을 하려다가 실패해서 ViewBinding과 코드가 섞여있음 (바인딩은 SignUpActivity.kt에서 설명)
 - 아이디/비밀번호 중 빈칸이 있다면 toast 화면 띄우기
 ```kt
 var emptyCheck = false
 ...
 binding.login.setOnClickListener {
            val id = binding.inputID.text.toString()
            val pwd = binding.inputPWD.text.toString()

            emptyCheck = id.isEmpty() || pwd.isEmpty()

            if(!emptyCheck){
                var intent1 = Intent(this, HomeActivity::class.java)
                startActivity(intent1)
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
```
id또는 pwd의 문자열이 empty라면 emptyCheck = false이다.
만일 문자열이 채워져 emptyCheck = true라면 Intent를 통해 HomeActivity로 이동하고 토스트 메세지를 띄워준다.

