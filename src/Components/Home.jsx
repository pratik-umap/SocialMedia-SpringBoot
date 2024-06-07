import React, { useEffect, useState } from 'react'
import Post from './Post'
import { useDispatch, useSelector } from 'react-redux'
import { getAllUser } from '../Action/User';
import { getFollowingPost, getFollowingStory } from '../Action/Post';
import { Link } from 'react-router-dom';
import { Dialog, Typography } from '@mui/material';


function Home() {
  const [disStory,setDisStory]= useState(null)
  const [isStory,setIsStory]= useState(false)
  const dispatch = useDispatch();
  const {users} = useSelector((state)=> state.alluser)
  const {posts} = useSelector((state)=> state.post)
  const {stories} = useSelector((state)=> state.story)
   let user = JSON.parse(localStorage.getItem('user')) 

   const displayStory = (id,isFollowing) =>{
    if (isFollowing) {
      stories?.followingstory.map((story)=>{
        if (story.id === id) {
         setIsStory(true)
         setDisStory(story)
         return;
        }
     })
    } else {
      stories?.mystory.map((story)=>{
        if (story.id === id) {
         setIsStory(true)
         setDisStory(story)
         return;
        }
     })
    }
  
   }
  useEffect(() => {
    dispatch(getAllUser())
    dispatch(getFollowingPost())
    dispatch(getFollowingStory())
  }, [dispatch])
  
  return (
    <div className='home'>
        <div className="home-left">
          <div className="stories">
                 {
                    stories?.mystory?.map((story)=>(
                      <div className='storyBox' onClick={()=> displayStory(story.id,false)}>
                        <img src="https://images.pexels.com/photos/6843561/pexels-photo-6843561.jpeg?auto=compress&cs=tinysrgb&w=400" alt="" className='storyImage' />
                        <h4>{story  && story.user.username}</h4>
                      </div>
                     ))
                  }
                 
                  {
                    stories?.followingstory?.map((story)=>(
                      <div className='storyBox' onClick={()=> displayStory(story.id,true)}>
                        <img src="https://images.pexels.com/photos/6843561/pexels-photo-6843561.jpeg?auto=compress&cs=tinysrgb&w=400" alt="" className='storyImage' />
                        <h4>{story  && story.user.username}</h4>
                      </div>
                     ))
                  }
          </div>
          {
            posts?.map((post) => 
            <Post 
               key={post.id}
               postId={post.id}
               location={post.location}
               image={post.image_path}
               desc={post.description}
               likes={post.likes == null ? [] : post.likes}
               comments={post.comments == null ? [] :post.comments}
               user={user}
            />)
          }
        </div>
        <div className="home-right">
          {
            users?.map((u)=>(
              user.id !== u.id ?
              <div className="userList" key={u.id}>
                  <img src="https://images.pexels.com/photos/6843561/pexels-photo-6843561.jpeg?auto=compress&cs=tinysrgb&w=400" alt="userImage" className='userPicture' />
                  <Link to={`/profile/${u.id}`}>
                     <h4>{u.username}</h4>
                  </Link>
                </div> : null
            ))
          }
        </div>
        <Dialog open={isStory} onClose={()=> setIsStory(!isStory)}>
               <div className="dialogbox">
                <Typography variant='h5'>Stories</Typography>
                     {
                      disStory && <img src="https://images.pexels.com/photos/6843561/pexels-photo-6843561.jpeg?auto=compress&cs=tinysrgb&w=400" alt="" className='postImage' />

                     }
               </div>
          </Dialog>
    </div>
  )
}

export default Home