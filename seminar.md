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

id또는 pwd의 문자열이 empty라면 emptyCheck = true이다.
만일 문자열이 채워져 emptyCheck = false라면 Intent를 통해 HomeActivity로 이동하고 토스트 메세지를 띄워준다.

![image](https://user-images.githubusercontent.com/84129098/162623188-86d60e6d-28f7-47b7-a679-5fe46eb4025a.png)

 - 회원가입 창으로 넘어가기
```kt
binding.join.setOnClickListener {
            val intent2 = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent2)
        }
```
join 버튼을 누르면 Intent를 통해 SignUpActivity로 이동
이때 이용한 resultLauncher는 뒤에서 설명하는 정보 받아오는 것임

 - SignActivity창에서 입력한 아이디/비밀번호 받아오기
```kt
private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                val userID = it.data?.getStringExtra("userID") ?: ""
                val userPWD = it.data?.getStringExtra("userPWD") ?: ""
                binding.inputID.setText(userID)
                binding.inputPWD.setText(userPWD)
            }
        }
```
registerForActivityResult를 통해 SignUpActivity에서 입력한 아이디/비밀번호를 SignInActivity으로 받아옴


## 2. SignUpActivity.kt
 - activity_signup.xml과 binding
```kt
private lateinit var binding: ActivitySignupBinding
override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        ...
```
위의 코드를 통해 바인딩 해준다

 - 아이디/비밀번호 중 빈칸이 있다면 toast 화면 띄우기 & 비밀번호와 비밀번호 확인이 일치하지 않으면 toast화면 띄우기 
```kt
    private var emptyCheck = false
    private var pwdCheck = false
    ...
```
```kt
val name = binding.inputName.text.toString()
            val name = binding.inputName.text.toString()
            val id = binding.inputID.text.toString()
            val pwd = binding.inputPWD.text.toString()
            val repwd = binding.inputREPWD.text.toString()
            
            emptyCheck = name.isEmpty()||id.isEmpty()||pwd.isEmpty()||repwd.isEmpty()
            pwdCheck = pwd == repwd

            if(!emptyCheck && pwdCheck) {
                Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                finish()
            }
            else{
                if(emptyCheck){
                    Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
                }
                else if(!pwdCheck && !emptyCheck){
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
```
이름/아이디/비밀번호/비밀번호 확인이 비어있다면 emptyCheck = true, 비번/비번확인이 같다면 pwdCheck=true
- emptyCheck = true라면 정보 입력 관련 toast창 띄우기

![image](https://user-images.githubusercontent.com/84129098/162623853-2333a679-f713-4a85-aa9e-0d9b3abfa666.png)

- pwdCheck=true이고 emptyCheck=false라면 비밀번호 확인 관련 toast창 띄우기

![image](https://user-images.githubusercontent.com/84129098/162623919-0e6ec24d-c4c4-411e-a4fe-fddc121d59fc.png)

- 모든 정보를 입력하고 비번까지 동일하다면 회원가입 완료 toast창 띄우고 finish()로 화면 종료

![image](https://user-images.githubusercontent.com/84129098/162623993-3caede2c-3ff4-4c9f-ae0f-239881747dac.png)

 - 회원가입 시에 입력한 아이디/비밀번호 로그인창으로 넘겨주기
```kt
val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("userID", binding.inputID.text.toString())
            intent.putExtra("userPWD", binding.inputPWD.text.toString())
            setResult(RESULT_OK, intent)
```
putExtra를 통해 입력한 아이디와 비밀번호를 SignInActivity로 보낸다

![image](https://user-images.githubusercontent.com/84129098/162624168-344d31f9-365d-4565-b948-30626d2bc8c1.png)

## 3. activity_home.xml
HomeActivity.kt는 바인딩만 해줬기때문에 생략한다.
 - ScrollView를 통해 많은 정보가 띄워졌을 때 스크롤 기능 추가
```kt
<ScrollView
        android:id="@+id/scroll"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:id="@+id/layoutView"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <ImageView
            ...
```
ScrollView 안에는 하나의 요소만 들어갈 수 있어서         <androidx.constraintlayout.widget.ConstraintLayout를 이용하여 ImagageView, TextView 등을 하나로 묶어줬다.

![image](https://user-images.githubusercontent.com/84129098/162624462-2ef158d6-0e84-49e8-b278-915db9c58637.png)
![image](https://user-images.githubusercontent.com/84129098/162624467-9bcc2134-9d41-44ba-9f6e-c13216e0e555.png)
