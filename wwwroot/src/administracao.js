const btnAddNewUserRef = document.querySelector("#openAddNewUser");
const btnCalenderClientsRef = document.querySelector("#openCalenderClients")
const btnAddNewDentisRef = document.querySelector("#openAddNewDentist");
const btnCalenderDentistRef = document.querySelector("#openCalenderDentist")
const btnCalenderConsultsRef = document.querySelector("#openCalendeConsults")
const AddNewUserRef = document.querySelector("#showCadastrarCliente")
const calendaClientRef = document.querySelector("#showAgendaDeClients")


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