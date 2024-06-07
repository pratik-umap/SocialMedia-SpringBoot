import React, { useState } from 'react'
import { useDispatch } from 'react-redux'
import { login } from '../Action/User'

function Login() {
    const [username,setUsername]=useState("")
    const [password,setPassword]=useState("")
    const dispatch = useDispatch()
    
    const submitHandler = (e) =>{
        e.preventDefault()
        dispatch(login(username,password))
    }
  return (
    <div className='loginform'>
        <form onSubmit={submitHandler}>
        <div className='login'>
            <div>
                <label >Username </label>
                <input type='text' className='description'   name={username} onChange={(e)=> setUsername(e.target.value)}/>
            </div>
            <div>
                <label>password </label>
                <input type='password' className='description'  name={password} onChange={(e)=> setPassword(e.target.value)}/>
            </div>
            <button type='submit' className='postBtn'>Login</button>

       </div>
        </form>
    </div>
  )
}

export default Login