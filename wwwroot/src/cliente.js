const inputSelectDentistRef = document.querySelector(".select_dentistaOption")

const btnSubmitRef = document.querySelector("#btnSubmit")

const select = document.querySelector("#dentista")

const option = document.querySelector('option')

const dataConsultRef = document.querySelector('input[type="date"]')
const hourConsultRef = document.querySelector('input[type="time"]')

// console.dir(dataConsultRef)

const showSelectDentist = () => {
    let requestHeaders = {
        headers: {
            "Content-Type":'application/json',
        }
    }
    fetch('http://localhost:8080/api/dentista',requestHeaders)
    .then(response => {
        response.json()
        .then(data => {
            let dados = data

              for(let dado of dados) {
                    select.innerHTML += 
                    // '<option value="' + dado.id + '">' + dado.name + '</option>';
                    `<option value="${dado.id}">Dentista ${dado.name} ${dado.lastName} Cro: ${dado.registration}</option>`                  
              }                                     
        })
        
    }) 

}


const teste = (select) => {
    
    // let tets = option.innerHTML
    // option.innerHTML = select.options[select.selectedIndex].value 
    // 'ID: <b>' + select.value + '</b>';

}
showSelectDentist()
const createConsult = () => {

    let consult = {
        dentist: {
            id: option.innerHTML = select.options[select.selectedIndex].value,
        },
        client: {
            id: 1,
        },
        updateAt : "",
        scheduledDate : dataConsultRef.value,
        scheduledTime : hourConsultRef.value

    }
    let requestHeaders = {
        'Content-Type': 'application/json'
    }

    let requestConfig = {
        method: 'POST',
        body: JSON.stringify(consult),
        headers: requestHeaders
    }
    fetch('http://localhost:8080/api/consulta',requestConfig)
    .then(response =>{
        response.json()
    })
}

// btnSelectRef.addEventListener('click', e => {
//     e.preventDefault()
  

// })

btnSubmitRef.addEventListener('click', e => {
    e.preventDefault()
    createConsult()
} )


//resolver questoa para async await ao criar o elemnto option
