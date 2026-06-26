// import './App.css'
import { BrowserRouter as Router, Navigate, Routes, Route, useLocation } from 'react-router-dom'
import { Button } from '@mui/material'
import { AuthContext } from 'react-oauth2-code-pkce';
import { useContext, useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { setCredentials } from './store/authSlice';
function App() {
  
  const { token , tokenData , login , logOut , isAuthenticated } = useContext(AuthContext);
const dispatch = useDispatch();
const [authReady , setAuthReady] = useState(false);

useEffect(() => {
if(token){
  dispatch(setCredentialsals({token,user:tokenData}));
  setAuthReady(true);
}
},[token,tokenData,dispatch]);

  return (
   <Router>
    {!token ? ( <Button variant="contained" onClick={()=> {login()}}>
  Login
</Button>):(
  <div>
    <pre>{JSON.stringify(tokenData, null, 2)}</pre>
  </div>
)}
   
    </Router>
  )
}

export default App
