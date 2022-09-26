const inputUsernameRef = document.querySelector('#input-email');
const inputPasswordLoginRef = document.querySelector('#input-password');
const inputBtnAccessRef = document.querySelector('#btn');



const loginUser = () =>{
    let userLogin = {
      username: inputUsernameRef.value,
      password: inputPasswordLoginRef.value
    }
  
    let requestHeaders = {
      'Content-Type': 'application/json'
    }
  
    let requestConfig = {
      method: 'POST',
      body: JSON.stringify(userLogin),
      headers: requestHeaders
    }
  
    fetch('http://localhost:8080/login', requestConfig)
  
     .then(response =>{
      if(response.ok) {
        response.json()
        // console.log(response)
        .then(data =>{
            console.log(data)
          localStorage.setItem('token', data.token)
          setTimeout(()=>{window.location.assign('./pages/adminstracao.html')},2000)
      })
  
    }
    else {
      alertIdentUserRef.classList.add('alertIdentUserShow')
    }
    })
  }
  
  inputBtnAccessRef.addEventListener('click', e => {
    e.preventDefault();
    loginUser();
  });