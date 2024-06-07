import React, { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { searchUser } from '../Action/User';

function SearchUser() {
  const[search,setSearch]=useState("")
  const dispatch = useDispatch()
  const {users} = useSelector((state)=> state.users);
  console.log(users);
  const submitHandler = (e) => {
    e.preventDefault();
    dispatch(searchUser(search))
  }
  return (
    <div className='searchContainer'>
        <div className="search">
            <form onSubmit={submitHandler}>
               <input type='text' value={search} onChange={(e)=> setSearch(e.target.value)} placeholder='search user'/>
               <button type='submit'>search</button>
            </form>
        </div>

        <div className='searchUser'>
           {
            users ? users.map(user =>(
              <div className="home-right">
                <div className="userList">
                  <img src="https://images.pexels.com/photos/6843561/pexels-photo-6843561.jpeg?auto=compress&cs=tinysrgb&w=400" alt="userImage" className='userPicture' />
                  <h4>{user.username}</h4>
                </div>
              </div>
            )) : null
           }
        </div>
    </div>
  )
}

export default SearchUser