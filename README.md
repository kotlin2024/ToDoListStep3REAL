# 인증 인가 기능이 추가된 TO-DO LIST API

간단한 카드 작성과 댓글 작성 그리고
사용자의 E-Mail과 password를 입력받아 회원가입 및 로그인이 가능하며
OAuth를 이용하여 Kakao 소셜 로그인을 이용할 수 있음


## Run the tests

    (http://localhost:8080)  
    


### 소셜 로그인 작동 예시

`ouath /login/kakao`

    try it out  
    

  
`Request URL`

    (http://localhost:8080/oauth2/login/kakao)  
    


  
`local host`

?code 뒤의 문자열을 가져옴 Lv_F4rAnZ1kusRWxMDB6qZqXyQ6d_zZ0rQDnGU_omQJLM60pA7jB-QAAAAQKPXUaAAABj_-L-RXMISgqRbFCUQ

    (http://localhost:8080/oauth2/callback/kakao?code=Lv_F4rAnZ1kusRWxMDB6qZqXyQ6d_zZ0rQDnGU_omQJLM60pA7jB-QAAAAQKPXUaAAABj_-L-RXMISgqRbFCUQ)  
    


  
`oauth2/login/callback`

try it out

    (Lv_F4rAnZ1kusRWxMDB6qZqXyQ6d_zZ0rQDnGU_omQJLM60pA7jB-QAAAAQKPXUaAAABj_-L-RXMISgqRbFCUQ)  
    
    


  
`Response body`

토큰이 생성됨

    (eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzNTE5MjY5MjQ3IiwiaXNzIjoidGVhbS5ISlAuY29tIiwiZXhwIjoxNzE3OTgzMTI0LCJ1c2VyUm9sZSI6Ik5PUk1BTCIsInVzZXJFbWFpbCI6IjM1MTkyNjkyNDcifQ.jKK_E0NWVTryW_Iv7km9pFWG564uw9IZeyUU6NCj78)  
    



해당 토큰으로 로그인


