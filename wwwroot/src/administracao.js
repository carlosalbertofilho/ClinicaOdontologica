const btnAddNewUserRef = document.querySelector("#openAddNewUser");
const btnCalenderClientsRef = document.querySelector("#openCalenderClients")
const btnAddNewDentisRef = document.querySelector("#openAddNewDentist");
const btnCalenderDentistRef = document.querySelector("#openCalenderDentist")
const btnCalenderConsultsRef = document.querySelector("#openCalendeConsults")
const AddNewUserRef = document.querySelector("#showCadastrarCliente")
const calendaClientRef = document.querySelector("#showAgendaDeClients")
const calendaDentistRef = document.querySelector("#showAgendaDeDentists")
const addNewDentisRef = document.querySelector("#showCadastrarDentista")

const listClientsRef = document.querySelector("#list_client")
const listDentistRef = document.querySelector("#list_dentist")


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
const selectAdminRef = document.querySelector('#isAdministrator');
const btnSubmitDentistaRef = document.querySelector("#submitDentist");


const showAddNewUser = () => {
    if(btnAddNewUserRef.click){
        AddNewUserRef.classList.add('show_cadastrar_cliente')
        btnAddNewUserRef.classList.add('active')
        addNewDentisRef.classList.remove('show_cadastrar_dentista')
        calendaClientRef.classList.remove('show_agenda_cliente')
        calendaDentistRef.classList.remove('show_dentista_cliente')
        btnCalenderClientsRef.classList.remove('active')
        btnAddNewDentisRef.classList.remove('active')
        btnCalenderDentistRef.classList.remove('active')
        btnCalenderConsultsRef.classList.remove('active')
    }
}


// POST CLIENTES

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



const showCalendarClients = () => {
    if(btnCalenderClientsRef.click){
        btnCalenderClientsRef.classList.add('active')
        calendaClientRef.classList.add('show_agenda_cliente')
        AddNewUserRef.classList.remove('show_cadastrar_cliente')
        addNewDentisRef.classList.remove('show_cadastrar_dentista')
        calendaDentistRef.classList.remove('show_dentista_cliente')
        btnAddNewDentisRef.classList.remove('active')
        btnAddNewUserRef.classList.remove('active')
        btnCalenderDentistRef.classList.remove('active')
        btnCalenderConsultsRef.classList.remove('active')
        
    }
}

// GET CLIENTES

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

// POST DENTISTAS

const createNewDentist = () => {
    let client = {
        name: inputNameDentistaRef.value,
        lastName: inputLastNameDentistaRef.value,
        login: inputEmailDentistaRef.value,
        password: inputPasswordDentistaRef.value,
        registration: inputRegistrationDentistaRef.value,
        isAdmin: selectAdminRef.value,
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


const showAddNewdentist = () => {
    if(btnAddNewDentisRef.click){

        btnAddNewDentisRef.classList.add('active')
        addNewDentisRef.classList.add('show_cadastrar_dentista')
        AddNewUserRef.classList.remove('show_cadastrar_cliente')
        calendaClientRef.classList.remove('show_agenda_cliente')
        calendaDentistRef.classList.remove('show_dentista_cliente')
        btnAddNewUserRef.classList.remove('active')
        btnCalenderClientsRef.classList.remove('active')
        btnCalenderDentistRef.classList.remove('active')
        btnCalenderConsultsRef.classList.remove('active')
    }
}

const showCalendarDentists = () => {
    if(btnAddNewDentisRef.click){

        btnCalenderDentistRef.classList.add('active')
        calendaDentistRef.classList.add('show_dentista_cliente')
        AddNewUserRef.classList.remove('show_cadastrar_cliente')
        calendaClientRef.classList.remove('show_agenda_cliente')
        addNewDentisRef.classList.remove('show_cadastrar_dentista')
        btnAddNewUserRef.classList.remove('active')
        btnCalenderClientsRef.classList.remove('active')
        btnAddNewDentisRef.classList.remove('active')
        btnCalenderConsultsRef.classList.remove('active')
    }
}

const showCalendarConsults = () => {
    if(btnAddNewDentisRef.click){

        btnCalenderConsultsRef.classList.add('active')
        AddNewUserRef.classList.remove('show_cadastrar_cliente')
        calendaClientRef.classList.remove('show_agenda_cliente')
        addNewDentisRef.classList.remove('show_cadastrar_dentista')
        calendaDentistRef.classList.remove('show_dentista_cliente')
        btnAddNewUserRef.classList.remove('active')
        btnCalenderClientsRef.classList.remove('active')
        btnCalenderDentistRef.classList.remove('active')
        btnAddNewDentisRef.classList.remove('active')
    }
}

showClients()
showDentists()
btnAddNewUserRef.addEventListener('click', showAddNewUser)
btnCalenderClientsRef.addEventListener('click', showCalendarClients)
btnAddNewDentisRef.addEventListener('click', showAddNewdentist)
btnCalenderDentistRef.addEventListener('click', showCalendarDentists)
btnCalenderConsultsRef.addEventListener('click', showCalendarConsults)

btnSubmitRef.addEventListener('click', e => {
    e.preventDefault();
    createNewClient();
})

btnSubmitDentistaRef.addEventListener('click', e => {
    e.preventDefault();
    createNewDentist();
})