import logo from './logo.svg';
import './App.css';
import axios from "axios";
import {useDropzone} from 'react-dropzone'
import React, {useState, useEffect, useCallback} from "react";

const Users = () => {
  const [users, setUsers] = useState([]);

const fetchUsers = () => {

  axios.get("http://localhost:8080/api/user").then(res => {
    console.log(res);
    setUsers(res.data);
  });
};

useEffect(() => {
  fetchUsers();
}, []);

return users.map((user, index) => {
  return (
  <div key={index}>
    {/* TODO: put image here */}
    <br/>
    <br/>
    <h1>{user.userName}</h1>
    <p>{user.userId}</p>
    <Dropzone />
    <br/>
  </div>
  )
});

};

function Dropzone() {
  const onDrop = useCallback(acceptedFiles => {
    // Do something with the files
    const file = acceptedFiles[0];
    console.log(file);
  }, [])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the image here ...</p> :
          <p class="imageUploader">Click here to upload user image.</p>
      }
    </div>
  )
}

function App() {
  return (
    <div className="App">
      <Users/>
    </div>
  );
}

export default App;
