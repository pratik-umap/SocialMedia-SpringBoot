import { Outlet } from 'react-router-dom';
import './App.css';
import Header from './Components/Header';
import { useSelector } from 'react-redux';
import  Login  from './Components/Login';


function App() {
   const {isAuthenticated} = useSelector((state)=> state.user)
  return (
    <div className="App">
     <Header /> 
     <Outlet />
      {/* {
        isAuthenticated ? 
        <>
          <Header /> 
          <Outlet />
        </> 
        : <Login />
      } */}
    </div>
  );
}

export default App;
