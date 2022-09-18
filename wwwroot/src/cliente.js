const inputSelectDentistRef = document.querySelector(".select_dentistaOption")

const btnSubmitRef = document.querySelector("#btnSubmit")

const inputDentist = document.querySelector("option")


console.log(inputDentist)

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
           
                    
                    dados.forEach(element => {
                        // let op = document.createElement("select")
                        let op1 = document.createElement("option")
                        op1.innerText = element;
                        op1.value = element.id
                        op1.innerText = `Dentista ${element.name} ${element.lastName} Cro: ${element.registration}` 
                        inputDentist.after(op1)
                        console.log(op1)
                    });
                    
                  
        })
        
    }) 
}


// const createConsult = () => {

//     let consult = {
//         dentist: {
//             id: inputDentist.value,
//         },
//         client: {
//             id: inputDentist.value,
//         },
//         updateAt : "2022-09-30",
//         scheduledDate : "2022-10-01",
//         scheduledTime : "10:00:00"

//     }
//     let requestHeaders = {
//         'Content-Type': 'application/json'
//     }

//     let requestConfig = {
//         method: 'POST',
//         body: JSON.stringify(consult),
//         headers: requestHeaders
//     }
//     fetch('http://localhost:8080/api/consulta',requestConfig)
//     .then(response =>{
//         response.json()
//     })
// }

// btnSelectRef.addEventListener('click', e => {
    // e.preventDefault()
    showSelectDentist()

// })

// btnSubmitRef.addEventListener('click', e => {
//     e.preventDefault()
//     createConsult()
// } )


//resolver questoa para async await ao criar o elemnto option