const btnAddNewUserRef = document.querySelector("#openAddNewUser");
const btnCalenderClientsRef = document.querySelector("#openCalenderClients")
const btnAddNewDentisRef = document.querySelector("#openAddNewDentist");
const btnCalenderDentistRef = document.querySelector("#openCalenderDentist")
const btnCalenderConsultsRef = document.querySelector("#openCalendeConsults")
const AddNewUserRef = document.querySelector("#showCadastrarCliente")
const calendaClientRef = document.querySelector("#showAgendaDeClients")

const inputNameRef = document.querySelector("#name");
const inputLastNameRef = document.querySelector("#lastName");
const inputEmailRef = document.querySelector("#email");
const inputPasswordRef = document.querySelector("#password");
const inputRgRef = document.querySelector("#register");
const inputAddressRef = document.querySelector("#address");
const btnSubmitRef = document.querySelector("#submitClient")

const showAddNewUser = () => {
    if(btnAddNewUserRef.click){
        AddNewUserRef.classList.add('show_cadastrar_cliente')
        btnAddNewUserRef.classList.add('active')
        calendaClientRef.classList.remove('show_agenda_cliente')
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
        btnAddNewDentisRef.classList.remove('active')
        btnAddNewUserRef.classList.remove('active')
        btnCalenderDentistRef.classList.remove('active')
        btnCalenderConsultsRef.classList.remove('active')
        
    }
}

const showAddNewdentist = () => {
    if(btnAddNewDentisRef.click){

        btnAddNewDentisRef.classList.add('active')
        AddNewUserRef.classList.remove('show_cadastrar_cliente')
        calendaClientRef.classList.remove('show_agenda_cliente')
        btnAddNewUserRef.classList.remove('active')
        btnCalenderClientsRef.classList.remove('active')
        btnCalenderDentistRef.classList.remove('active')
        btnCalenderConsultsRef.classList.remove('active')
    }
}

const showCalendarDentists = () => {
    if(btnAddNewDentisRef.click){

        btnCalenderDentistRef.classList.add('active')
        AddNewUserRef.classList.remove('show_cadastrar_cliente')
        calendaClientRef.classList.remove('show_agenda_cliente')
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
        btnAddNewUserRef.classList.remove('active')
        btnCalenderClientsRef.classList.remove('active')
        btnCalenderDentistRef.classList.remove('active')
        btnAddNewDentisRef.classList.remove('active')
    }
}

btnAddNewUserRef.addEventListener('click', showAddNewUser)
btnCalenderClientsRef.addEventListener('click', showCalendarClients)
btnAddNewDentisRef.addEventListener('click', showAddNewdentist)
btnCalenderDentistRef.addEventListener('click', showCalendarDentists)
btnCalenderConsultsRef.addEventListener('click', showCalendarConsults)

btnSubmitRef.addEventListener('click', e => {
    e.preventDefault();
    createNewClient();
})