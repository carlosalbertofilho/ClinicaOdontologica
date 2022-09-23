const inputEmailRef = document.querySelector('#input-email');
const inputPasswordRef = document.querySelector('#input-password');
const inputBtnAccessRef = document.querySelector('#btn');



const loginUser = () =>{
    let userLogin = {
      email: inputEmailRef.value,
      password: inputPasswordRef.value
    }
  
    let requestHeaders = {
      'Content-Type': 'application/json'
    }
  
    let requestConfig = {
      method: 'POST',
      body: JSON.stringify(userLogin),
      headers: requestHeaders
    }
  
    fetch('http://localhost:8080/api/login', requestConfig)
  
     .then(response =>{
      if(response.ok) {
        response.json()
        // console.log(response)
        .then(data =>{
            console.log(data)
          localStorage.setItem('token', data.jwt)
          setTimeout(()=>{window.location.assign('./pages/adminstracao.html')},2000)
      })
  
    }
    else {
      alertIdentUserRef.classList.add('alertIdentUserShow')
    }
    })
  }
  loginUser();
  inputBtnAccessRef.addEventListener('click', e => {
    e.preventDefault();
    
  });