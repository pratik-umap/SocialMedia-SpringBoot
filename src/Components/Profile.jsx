import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { useParams } from 'react-router-dom'
import { followUser, getUserProfile, unfollow } from '../Action/User';
import Post from './Post';
import { Button } from '@mui/material';

function Profile() {
    const {id} =  useParams();
    const [following,setFollowing] = useState(false)
    const dispatch = useDispatch()
    
    const {info} = useSelector((state)=> state.profile)
    let user = JSON.parse(localStorage.getItem('user'))
    console.log(info);
    const handleFollow = () =>{
        setFollowing(!following)

        if (info && info.followUser) {
            dispatch(unfollow(id))
        } else {
            dispatch(followUser(id))
        }
        dispatch(getUserProfile(id))
    }
    useEffect(()=>{
        if (info && info.followUser) {
            setFollowing(true)
        }
        dispatch(getUserProfile(id))
    },[])

  return (
    <div className='account'>
        <div className="account-left">
            <div className="allPost">
            {
             info && info.posts.length > 0 ? info.posts.map((post) => 
              <Post 
                key={post.id}
                postId={post.id}
                location={post.location}
                image={post.image_path}
                desc={post.description}
                likes={post.likes == null ? [] : post.likes}
                comments={post.comments == null ? [] :post.comments}
                user={user}
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
                <Button variant="contained"  style={{ background: info && info.followUser ? "red":""}} onClick={handleFollow}>
                    {info && info.followUser ? "unfollow" : "follow"} </Button>
                    
        
        </div>
    </div>
  )
}

export default Profile