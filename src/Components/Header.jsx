import React from 'react'
import { Link } from 'react-router-dom'

function Header() {
  return (
    <div className='navbar'> 
        <ul>
            <Link to={"/home"}><li style={{textDecoration:"none",color:'white'}}>Home</li></Link>
            <Link to={"/post"}><li style={{textDecoration:"none",color:'white'}}>Post</li></Link>
            <Link to={"/story"}><li style={{textDecoration:"none",color:'white'}}>Story</li></Link>
            <Link to={"/search"}><li style={{textDecoration:"none",color:'white'}}>Search</li></Link>
            <Link to={"/account"}><li style={{textDecoration:"none",color:'white'}}>Account</li></Link>
        </ul>
    </div>
  )
}

export default Header