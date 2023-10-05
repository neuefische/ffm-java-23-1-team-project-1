import './App.css'
import {useState} from "react";
import axios from "axios";

export default function App() {

  const [hello, setHello] =  useState("")

  axios.get("api/hello")
      .then(response => {setHello(response.data)})
      .catch(reason => console.log(reason))
      .finally(() => console.log("fertig"))

  return (
    <>
      <h3>
        {hello}
      </h3>
    </>
  )
}