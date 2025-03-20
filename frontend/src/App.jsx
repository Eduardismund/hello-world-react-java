import { useState } from 'react'
import {fetchHello} from "./HelloClient.js"

function App() {
    const [message, setMessage] = useState('click me!')

    function onClick(){
        fetchHello().then(hello => setMessage(hello) )
    }

    return (
        <>
            <div className="card">
                <button onClick={onClick}>{message}</button>
            </div>
        </>
    )
}

export default App
