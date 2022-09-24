const btnAddNewConsultRef = document.querySelector("#openAddNewConsult")
const btnConsultsRef = document.querySelector("#openCalenderConsults")
const btnAllConsultsRef = document.querySelector("#openAllConsults")

const showCadastrarConsultRef = document.querySelector("#showCadastrarConsult")
const showConsultClientRef = document.querySelector("#showConsultClient")


const inputSelectDentistRef = document.querySelector(".select_dentistaOption")
const btnSubmitRef = document.querySelector("#btnSubmit")
const select = document.querySelector("#dentista")
const option = document.querySelector('option')
const dataConsultRef = document.querySelector('input[type="date"]')
const hourConsultRef = document.querySelector('input[type="time"]')

const cardConsultRef = document.querySelector("#cardConsult")


const showAddConsult = () => {
    if(btnAddNewConsultRef.click) {
        showCadastrarConsultRef.classList.add('show_cadastrar_consult')
        btnAddNewConsultRef.classList.add('active')
        btnAllConsultsRef.classList.remove('active')
        btnConsultsRef.classList.remove('active')
        showConsultClientRef.classList.remove('show_consult_cliente')
    }
}

const showConsultsById = () => {
    if(btnConsultsRef.click) {

        showConsultClientRef.classList.add('show_consult_cliente')
        btnConsultsRef.classList.add('active')
        showCadastrarConsultRef.classList.remove('show_cadastrar_consult')
        btnAllConsultsRef.classList.remove('active')
        btnAddNewConsultRef.classList.remove('active')

    }
}

const showHistoricConsult = () => {
    if(btnAllConsultsRef.click){
        showCadastrarConsultRef.classList.remove('show_cadastrar_consult')
        btnAllConsultsRef.classList.add('active')
        btnAddNewConsultRef.classList.remove('active')
        showConsultClientRef.classList.remove('show_consult_cliente')
        btnConsultsRef.classList.remove('active')
    }
}

//GET de dentistas
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

// POST consulta
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

//GET consulta by id

const showConsultClient = (id=1) => { //falta token para rever o id
    let requestHeaders = {
        headers: {
            "Content-Type":'application/json',
        }
    }
    fetch(`http://localhost:8080/api/consulta/${id}`,requestHeaders)
    .then(response => {
        response.json()
        .then(data => {
            let dados = data
              cardConsultRef.innerHTML += `
                    <div class="client">
                        <img src="../img/User_icon_black.png" alt="">
                        <p>${dados.client.name} ${dados.client.lastName}</p>
                    </div>
                    <div class="client">
                        <img src="../img/Medical Doctor_icon_black.png" alt="">
                        <p>Dentista ${dados.dentist.name} ${dados.dentist.lastName} Cro: ${dados.dentist.registration}</p>
                    </div>
                    <div class="client">
                    <img src="../img/Schedule_icon_black.png" alt="">
                    <p>${dados.scheduledDate} - ${dados.scheduledTime}</p>
                    </div>
              `                                    
        })
        
    }) 
}

showConsultClient()

btnAddNewConsultRef.addEventListener('click', showAddConsult)
btnConsultsRef.addEventListener('click', showConsultsById)
btnAllConsultsRef.addEventListener('click', showHistoricConsult)

btnSubmitRef.addEventListener('click', e => {
    e.preventDefault()
    createConsult()
} )


//resolver questoa para async await ao criar o elemnto option
