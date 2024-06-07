import React, { useEffect } from 'react'
import Post from './Post'
import { useDispatch, useSelector } from 'react-redux'
import { getAccountInfo, getMyPost } from '../Action/Post'
import { Button } from '@mui/material'
import { logout } from '../Action/User'
import { useNavigate } from 'react-router-dom'
function UserAccount() {
  const dispatch = useDispatch()
  const navigate = useNavigate()
  const {posts} = useSelector((state)=> state.post)
  const {info} = useSelector((state)=> state.account)

  let user = JSON.parse(localStorage.getItem('user'))

  const logoutHandler = async() =>{
    await dispatch(logout())
    navigate("/login")
  }
  useEffect(()=>{
    dispatch(getMyPost())
    dispatch(getAccountInfo())
  },[])
  return (
    <div className='account'>
        <div className="account-left">
            <div className="allPost">
            {
             posts && posts.length > 0 ? posts.map((post) => 
              <Post 
                key={post.id}
                postId={post.id}
                location={post.location}
                image={post.image_path}
                desc={post.description}
                likes={post.likes == null ? [] : post.likes}
                comments={post.comments == null ? [] :post.comments}
                user={user}
                isAccount={true}
              />):null
          }
            </div>
        </div>
        <div className="account-right">
            <img src="https://images.pexels.com/photos/6843561/pexels-photo-6843561.jpeg?auto=compress&cs=tinysrgb&w=400" alt="user image" className='accountPicture' />

                <p style={{margin:"10px 0"}}>{user.name}</p>
                <p>Post  </p>
                <p>{info && info.post}</p>
                <p>following</p>
                <p>{info && info.following}</p>
                <p>followers</p>
                <p>{info && info.following}</p>
                <p>change account info</p>
                <Button variant="contained" onClick={logoutHandler}>Logout</Button>
        </div>
    </div>
  )
}

export default UserAccount