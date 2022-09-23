//Referencias vindo do Aside, para abrir e fechar o conteudo da main e deixar o link ativo
const btnAddNewUserRef = document.querySelector("#openAddNewUser");
const btnCalenderClientsRef = document.querySelector("#openCalenderClients")
const btnAddNewDentisRef = document.querySelector("#openAddNewDentist");
const btnCalenderDentistRef = document.querySelector("#openCalenderDentist")
const btnCalenderConsultsRef = document.querySelector("#openCalendeConsults")
const AddNewUserRef = document.querySelector("#showCadastrarCliente")
const calendaClientRef = document.querySelector("#showAgendaDeClients")
const calendaDentistRef = document.querySelector("#showAgendaDeDentists")
const addNewDentisRef = document.querySelector("#showCadastrarDentista")
const calendaConsultRef = document.querySelector("#showAgendaDeConsultas")
const btnAddNewConsultRef = document.querySelector("#openAddNewConsult")
const showCadastrarConsultRef = document.querySelector("#showCadastrarConsult")
const listClientsRef = document.querySelector("#list_client")
const listDentistRef = document.querySelector("#list_dentist")
const listConsultRef = document.querySelector("#list_consult")


// inputs id cadastrar cliente
const inputNameRef = document.querySelector("#name");
const inputLastNameRef = document.querySelector("#lastName");
const inputEmailRef = document.querySelector("#email");
const inputPasswordRef = document.querySelector("#password");
const inputRgRef = document.querySelector("#register");
const inputAddressRef = document.querySelector("#address");
const btnSubmitRef = document.querySelector("#submitClient");

// inputs id cadastrar dentista
const inputNameDentistaRef = document.querySelector("#nameDentista");
const inputLastNameDentistaRef = document.querySelector("#lastNameDentis");
const inputEmailDentistaRef = document.querySelector("#emailDentis");
const inputPasswordDentistaRef = document.querySelector("#passwordDentis");
const inputRegistrationDentistaRef = document.querySelector("#registrationDentist");
// const selectAdminRef = document.querySelector('#isAdministrator');
const btnSubmitDentistaRef = document.querySelector("#submitDentist");

//Inputs id cadastrar consulta
const inputSelectDentistRef = document.querySelector(".select_dentistaOption")
const selectDentistRef = document.querySelector("#dentista")
const selectClientRef = document.querySelector("#cliente")
const optionDentisRef = document.querySelector('option')
const optionClientRef = document.querySelector('option')
const dataConsultRef = document.querySelector('input[type="date"]')
const hourConsultRef = document.querySelector('input[type="time"]')
const btnSubmitConsultRef = document.querySelector("#btnSubmit")

// Mostrar conteudo adionar cliente
const showAddNewUser = () => {
    if(btnAddNewUserRef.click){
        AddNewUserRef.classList.add('show_cadastrar_cliente')
        btnAddNewUserRef.classList.add('active')
        addNewDentisRef.classList.remove('show_cadastrar_dentista')
        calendaClientRef.classList.remove('show_agenda_cliente')
        calendaDentistRef.classList.remove('show_dentista_cliente')
        calendaConsultRef.classList.remove('show_agenda_consulta')
        showCadastrarConsultRef.classList.remove('show_cadastrar_consult')
        btnCalenderClientsRef.classList.remove('active')
        btnAddNewDentisRef.classList.remove('active')
        btnCalenderDentistRef.classList.remove('active')
        btnCalenderConsultsRef.classList.remove('active')
        btnAddNewConsultRef.classList.remove('active')
    }
}
// POST cliente
const createNewClient = () => {
    let client = {
        name: inputNameRef.value,
        lastName: inputLastNameRef.value,
        login: inputEmailRef.value,
        password: inputPasswordRef.value,
        rg: inputRgRef.value,
        address: inputAddressRef.value,
    }

    let requestHeaders = {
        'Content-Type': 'application/json'
    }

    let requestConfig = {
        method: 'POST',
        body: JSON.stringify(client),
        headers: requestHeaders
    }
    fetch('http://localhost:8080/api/cliente',requestConfig)
    .then(response =>{
        response.json()
    })
}

// GET CLIENTES
const showCalendarClients = () => {
    if(btnCalenderClientsRef.click){
        btnCalenderClientsRef.classList.add('active')
        calendaClientRef.classList.add('show_agenda_cliente')
        AddNewUserRef.classList.remove('show_cadastrar_cliente')
        addNewDentisRef.classList.remove('show_cadastrar_dentista')
        calendaDentistRef.classList.remove('show_dentista_cliente')
        calendaConsultRef.classList.remove('show_agenda_consulta')
        showCadastrarConsultRef.classList.remove('show_cadastrar_consult')
        btnAddNewDentisRef.classList.remove('active')
        btnAddNewUserRef.classList.remove('active')
        btnCalenderDentistRef.classList.remove('active')
        btnCalenderConsultsRef.classList.remove('active')
        btnAddNewConsultRef.classList.remove('active')
        
    }
}
// Mostrar lista de clintes
const showClients = () => {

    let requestHeaders = {
        headers: {
            "Content-Type":'application/json',
        }
    }
    fetch('http://localhost:8080/api/cliente',requestHeaders)
    .then(response => {
        response.json()
        .then(data => {
            let dados = data
            for(let dado of dados){
                if(!dado.completed){
                    console.log("passou")
                    listClientsRef.innerHTML += `
                        <li class="item_client">
                            <div class="card_cliente_consult">
                                 <div class="client">
                                    <img src="../img/User.png" alt="">
                                    <p>${dado.name} ${dado.lastName}</p>
                                </div>
                                <div class="icons">
                                    <img src="..//img/Edit.png" alt="">
                                    <img onclick = "deleteClient(${dado.id})" src="..//img/Delete.png" alt="">
                                </div>
                            </div>
                        </li>
                        `
                }else{
                    console.log('nao passou')
                }
            }
        })
    })
}

showClients()

// DELETE CLIENTE

const deleteClient = (id) => {
    let requestHeaders = {
        "Content-Type": "application/json"
    }
    let requestConfig = {

        method: 'DELETE',
        headers: requestHeaders

    }

    fetch(`http://localhost:8080/api/cliente/${id}`, requestConfig)
    .then(response => {
        response.json()
        if(response.ok){
            console.log("Deletado")
        }else {
            console.log("Sem dados")
        }
    })
    
}
// Mostrar conteudo adionar dentista
const showAddNewdentist = () => {
    if(btnAddNewDentisRef.click){

        btnAddNewDentisRef.classList.add('active')
        addNewDentisRef.classList.add('show_cadastrar_dentista')
        AddNewUserRef.classList.remove('show_cadastrar_cliente')
        calendaClientRef.classList.remove('show_agenda_cliente')
        calendaDentistRef.classList.remove('show_dentista_cliente')
        calendaConsultRef.classList.remove('show_agenda_consulta')
        showCadastrarConsultRef.classList.remove('show_cadastrar_consult')
        btnAddNewUserRef.classList.remove('active')
        btnCalenderClientsRef.classList.remove('active')
        btnCalenderDentistRef.classList.remove('active')
        btnCalenderConsultsRef.classList.remove('active')
        btnAddNewConsultRef.classList.remove('active')
    }
}

// POST DENTISTAS
const createNewDentist = () => {
    let client = {
        name: inputNameDentistaRef.value,
        lastName: inputLastNameDentistaRef.value,
        login: inputEmailDentistaRef.value,
        password: inputPasswordDentistaRef.value,
        registration: inputRegistrationDentistaRef.value,
        // isAdmin: selectAdminRef.value,
    }

    let requestHeaders = {
        'Content-Type': 'application/json'
    }

    let requestConfig = {
        method: 'POST',
        body: JSON.stringify(client),
        headers: requestHeaders
    }
    fetch('http://localhost:8080/api/dentista',requestConfig)
    .then(response =>{
        response.json()
    })
}
// Mostrar conteudo lista de dentistas
const showCalendarDentists = () => {
    if(btnAddNewDentisRef.click){

        btnCalenderDentistRef.classList.add('active')
        calendaDentistRef.classList.add('show_dentista_cliente')
        AddNewUserRef.classList.remove('show_cadastrar_cliente')
        calendaClientRef.classList.remove('show_agenda_cliente')
        addNewDentisRef.classList.remove('show_cadastrar_dentista')
        calendaConsultRef.classList.remove('show_agenda_consulta')
        showCadastrarConsultRef.classList.remove('show_cadastrar_consult')
        btnAddNewUserRef.classList.remove('active')
        btnCalenderClientsRef.classList.remove('active')
        btnAddNewDentisRef.classList.remove('active')
        btnCalenderConsultsRef.classList.remove('active')
        btnAddNewConsultRef.classList.remove('active')
    }
}

// GET DENTISTAS
const showDentists = () => {

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
            for(let dado of dados){
                if(!dado.completed){
                    console.log("passou")
                    listDentistRef.innerHTML += `
                        <li class="item_client">
                            <div class="card_cliente_consult">
                                 <div class="client">
                                    <img src="../img/User.png" alt="">
                                    <p>${dado.name} ${dado.lastName}</p>
                                    <p>Cro: ${dado.registration}</p>
                                </div>
                                <div class="icons">
                                    <img src="..//img/Edit.png" alt="">
                                    <img onclick = "deleteDentist(${dado.id})" src="..//img/Delete.png" alt="">
                                </div>
                            </div>
                        </li>
                        `
                }else{
                    console.log('nao passou')
                }
            }
        })
    })
}

showDentists()

// DELETE Dentista
const deleteDentist = (id) => {
    let requestHeaders = {
        "Content-Type": "application/json"
    }
    let requestConfig = {

        method: 'DELETE',
        headers: requestHeaders

    }

    fetch(`http://localhost:8080/api/dentista/${id}`, requestConfig)
    .then(response => {
        response.json()
        if(response.ok){
            console.log("Deletado")
        }else {
            console.log("Sem dados")
        }
    })
    
}
// Mostrar conteudo adionar consulta
const showAddConsult = () => {
    if(btnAddNewConsultRef.click) {
        showCadastrarConsultRef.classList.add('show_cadastrar_consult')
        btnAddNewConsultRef.classList.add('active')
        AddNewUserRef.classList.remove('show_cadastrar_cliente')
        calendaClientRef.classList.remove('show_agenda_cliente')
        addNewDentisRef.classList.remove('show_cadastrar_dentista')
        calendaDentistRef.classList.remove('show_dentista_cliente')
        calendaConsultRef.classList.remove('show_agenda_consulta')
        btnAddNewUserRef.classList.remove('active')
        btnCalenderClientsRef.classList.remove('active')
        btnCalenderDentistRef.classList.remove('active')
        btnAddNewDentisRef.classList.remove('active')
        btnCalenderConsultsRef.classList.remove('active')
        
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
                selectDentistRef.innerHTML += 
                    // '<option value="' + dado.id + '">' + dado.name + '</option>';
                    `<option value="${dado.id}">Dentista ${dado.name} ${dado.lastName} Cro: ${dado.registration}</option>`                  
              }                                     
        })
        
    }) 

}
//GET de dentistas
const showSelectClient = () => {
    let requestHeaders = {
        headers: {
            "Content-Type":'application/json',
        }
    }
    fetch('http://localhost:8080/api/cliente',requestHeaders)
    .then(response => {
        response.json()
        .then(data => {
            let dados = data

              for(let dado of dados) {
                selectClientRef.innerHTML += 
                    // '<option value="' + dado.id + '">' + dado.name + '</option>';
                    `<option value="${dado.id}"> ${dado.name} ${dado.lastName} </option>`                  
              }                                     
        })
        
    }) 

}


const teste = (select) => {
    
    // let tets = option.innerHTML
    // option.innerHTML = select.options[select.selectedIndex].value 
    // 'ID: <b>' + select.value + '</b>';

}
showSelectClient()
showSelectDentist()

// POST consulta
const createConsult = () => {

    let consult = {
        dentist: {
            id: optionDentisRef.innerHTML = selectDentistRef.options[selectDentistRef.selectedIndex].value,
        },
        client: {
            id: optionClientRef.innerHTML = selectClientRef.options[selectClientRef.selectedIndex].value,
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

// Mostrar conteudo lista dentista
const showCalendarConsults = () => {
    if(btnAddNewDentisRef.click){

        btnCalenderConsultsRef.classList.add('active')
        calendaConsultRef.classList.add('show_agenda_consulta')
        AddNewUserRef.classList.remove('show_cadastrar_cliente')
        calendaClientRef.classList.remove('show_agenda_cliente')
        addNewDentisRef.classList.remove('show_cadastrar_dentista')
        calendaDentistRef.classList.remove('show_dentista_cliente')
        showCadastrarConsultRef.classList.remove('show_cadastrar_consult')
        btnAddNewUserRef.classList.remove('active')
        btnCalenderClientsRef.classList.remove('active')
        btnCalenderDentistRef.classList.remove('active')
        btnAddNewDentisRef.classList.remove('active')
        btnAddNewConsultRef.classList.remove('active')
    }
}

// GET CONSULTAS
const showConsults = () => {

    let requestHeaders = {
        headers: {
            "Content-Type":'application/json',
        }
    }
    fetch('http://localhost:8080/api/consulta',requestHeaders)
    .then(response => {
        response.json()
        .then(data => {
            let dados = data
            for(let dado of dados){
                if(!dado.completed){
                    console.log(dado)
                    listConsultRef.innerHTML += `
                            <li class="item_client">
                                 <div class="card_dentista_consult">
                                    <div class="client">
                                        <img src="../img/Medical Doctor.png" alt="">
                                         <p>${dado.dentist.name} ${dado.dentist.lastName}</p>
                                    </div>
                                    <div class="client">
                                        <img src="../img/User.png" alt="">
                                        <p>${dado.client.name} ${dado.client.lastName}</p>
                                    </div>
                                    <div class="client">
                                        <img src="../img/Schedule.png" alt="">
                                        <p>${dado.scheduledDate} ${dado.scheduledTime}</p>
                                    </div>
                                    <div class="icons">
                                        <img src="..//img/Edit.png" alt="">
                                         <img onclick = "deleteConsult(${dado.id})" src="..//img/Delete.png" alt="">
                                    </div>
                                </div>
                            </li>
                        `
                }else{
                    console.log('nao passou')
                }
            }
        })
    })
}

// DELETE consulta

const deleteConsult = (id) => {
    let requestHeaders = {
        "Content-Type": "application/json"
    }
    let requestConfig = {

        method: 'DELETE',
        headers: requestHeaders

    }

    fetch(`http://localhost:8080/api/consulta/${id}`, requestConfig)
    .then(response => {
        response.json()
        if(response.ok){
            console.log("Deletado")
        }else {
            console.log("Sem dados")
        }
    })
    
}
showConsults()

btnAddNewUserRef.addEventListener('click', showAddNewUser)
btnAddNewDentisRef.addEventListener('click', showAddNewdentist)
btnCalenderDentistRef.addEventListener('click', showCalendarDentists)
btnAddNewConsultRef.addEventListener('click', showAddConsult)
btnCalenderConsultsRef.addEventListener('click', showCalendarConsults)

btnSubmitRef.addEventListener('click', e => {
    e.preventDefault();
    createNewClient();
})
btnCalenderClientsRef.addEventListener('click', e =>{
    e.preventDefault()
    showCalendarClients()
})

btnSubmitDentistaRef.addEventListener('click', e => {
    e.preventDefault();
    createNewDentist();
})

btnSubmitConsultRef.addEventListener('click', e => {
    e.preventDefault()
    createConsult()
})