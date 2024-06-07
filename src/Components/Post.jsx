import React, { useEffect, useState } from 'react'
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ChatBubbleOutlineIcon from '@mui/icons-material/ChatBubbleOutline';
import { Dialog, Typography } from '@mui/material';
import { useDispatch, useSelector } from 'react-redux';
import { addComment, deleteComment, getFollowingPost, getMyPost, likePost, unlikePost } from '../Action/Post';
import DeleteIcon from '@mui/icons-material/Delete';

function Post({postId,location,image,desc,likes=[],comments=[],user,isAccount=false}) {
    const [likeBtn,setLikeBtn]=useState(false)
    const [likeUser,setLikeUser]=useState(false)
    const [comment,setComment]=useState(false)
    const [commentValue,setCommentValue]=useState("")

    const dispatch = useDispatch();
     const {message} = useSelector((state)=> state.likeUnlike) 
    useEffect(()=>{
       for (let i = 0; i < likes.length; i++) {
           if (likes[i].id === user.id) {
              setLikeBtn(true)
           }
       }

    },[user.id,likes])

    const handlerLike = async()=>{
          setLikeBtn(!likeBtn);
        if (likeBtn) {
            await dispatch(unlikePost(postId))
        } else {
            await dispatch(likePost(postId))
        }

        if (isAccount) {
            dispatch(getMyPost())
        } else {  
            dispatch(getFollowingPost())
        }
    }

    const submithandler = async(e) =>{
        e.preventDefault();
        await dispatch(addComment(commentValue,postId))
        setCommentValue("")
        if (isAccount) {
            dispatch(getMyPost())
        } else {  
            dispatch(getFollowingPost())
        }
    }

    const deleteCommentHandler = async(commentId) =>{
       await dispatch(deleteComment(commentId))
        if (isAccount) {
           dispatch(getMyPost())
        } else {  
            dispatch(getFollowingPost())
        }
    }
  return (
    <div className='post' >
        <div className="userProfile">
            <img src="https://images.pexels.com/photos/6843561/pexels-photo-6843561.jpeg?auto=compress&cs=tinysrgb&w=400" alt="user image" className='userPicture' />
             <div className="userInfo">
                <h5>{user.username}</h5>
                <h5>{location}</h5>
             </div>
        </div>
        <div className="postImg">
        <img src={image} alt="user image" className='postImage' />
        </div>
        <div className="icons" >
    
                <button onClick={handlerLike} className='btn' style={{background:"none",border:"none"}}>
                    {likeBtn ? <FavoriteIcon style={{color:"red"}} />  : <FavoriteBorderIcon />}
                </button>
            
            <button style={{background:"none",border:"none"}} className='btn'>
                <ChatBubbleOutlineIcon onClick={()=> setComment(!comment)}/> 
            </button>
        <h5>{desc}</h5>
        </div>
        <button onClick={()=> setLikeUser(!likeUser)} style={{background:"none",border:"none",marginLeft:"15px"}}>{ likes.length} Likes</button>

          <Dialog open={likeUser} onClose={()=> setLikeUser(!likeUser)}>
               <div className="dialogbox">
                   <Typography variant='h4'>Liked By</Typography>
                   <br />
               {
                    likes? likes.map(user =>(
                    <div>
                        <div className="userList">
                          <img src="https://images.pexels.com/photos/6843561/pexels-photo-6843561.jpeg?auto=compress&cs=tinysrgb&w=400" alt="userImage" className='userPicture' />
                          <h4>{user.username}</h4>
                        </div>
                    </div>
                    )) : null
               }
               </div>
          </Dialog>
          <Dialog open={comment} onClose={()=> setComment(!comment)}>
               <div className="dialogbox">
                <Typography variant='h5'>Comments</Typography>
                   <form className='form' onSubmit={submithandler}>
                       <input type='text' value={commentValue} onChange={(e)=> setCommentValue(e.target.value)} />
                       <button type='submit'>Add</button>
                   </form>

                   {
                    comments? comments.map(c =>(
                    <div>
                        <div className="userList">
                           <h4 style={{marginLeft:"10px"}}>{c.description}</h4>
                           <h4 style={{marginLeft:"50px"}}>
                           {
                              user.id === c.user.id ? <DeleteIcon onClick={()=> deleteCommentHandler(c.id)}/> : null
                           }
                           </h4>
                        </div>
                    </div>
                    )) : null
               }
               </div>
          </Dialog>
    </div>
  )
}

export default Post