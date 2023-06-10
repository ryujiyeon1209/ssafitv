import Vue from 'vue'
import Vuex from 'vuex'
import router from '../router'
import axios from 'axios'
import Swal from 'sweetalert2'

Vue.use(Vuex)

const REST_API = `http://localhost:9999/ssafit`;

export default new Vuex.Store({
  state: {
    user:{},      //사용자
    userIdCheck : 0,  //아이디중복확인
    userNickNameCheck : 0,  //닉네임 중복확인
    tmpUser: {},   //임시 비밀번호 사용자
    userList:[],  //사용자리스트
    video : {} ,  //비디오
    videoList : [], //비디오리스트
    review : {},    //리뷰
    reviewList : [],   //리뷰리스트
    allReviewList : [], //모든 리뷰리스트
    likevideo : {} ,  //찜비디오
    likevideoList : [], //찜한 비디오리스트
    heart : 0,      //좋아요(하트)
    calender: {},  //캘린더
  },
  getters: {
  },
  mutations: {
    //회원가입하기
    CREATE_USER: function (state, user) {
      state.userList.push(user);
    },

    //사용자 확인하기
    GET_USER: function(state, user){
      state.user=user;
    },

    //사용자 리스트 가져오기
    GET_USERLIST : function(state, userList){
      state.userList = userList;
    },

    //리뷰리스트 가져오기
    GET_REVIEWlLIST : function(state, reviewList){
      state.reviewList = reviewList;
    },

    //리뷰등록
    CREATE_REVIEW : function(state, review){
       state.reviewList.push(review);
       state.review = review;
    },

    //리뷰 가져오기
    GET_REVIEW : function(state, review){
      state.review = review;
    },

    //비디오 등록
    ADD_VIDEO(state, video) {
      state.videoList.push(video);
    },

    //비디오 가져오기
    GET_ViDEO(state, video) {
      state.video = video;
    },
    
    //비디오리스트 가져오기
    GET_VIDEOS(state, videoList) {
      state.videoList = videoList;
    },

    //찜한 비디오리스트 가져오기
    GET_LIKEVIDEOLIST(state, likevideoList){
      state.likevideoList=likevideoList;
      console.log("state.likevideoList : " +state.likevideoList)
    },
    
    //찜 비디오 등록하기
    GET_LIKEViDEO(state, data) {
      state.likevideo = data;
    },
    
    //로그아웃
    LOGOUT_USER: function (state) {
      state.user = null;
    },
    
    //임시 비밀번호 받기
    GET_TMPUSER(state, user){
      state.tmpUser = user;
    },

    //기록 가져오기
    GET_CALENDER(state, calender){
      state.calender=calender;
    },

    //찜 기록 가져오기
    GET_HEART(state, data){
      state.heart = data;
    }

  },
  actions: {
    //비디오 등록
    addVideo({ commit, dispatch }, video) {
      const API_URL = `${REST_API}/video`
      axios({
        url: API_URL,
        method: "POST",
        data: video,
      })
        .then(() => {
          commit("ADD_VIDEO", video);
          // dispatch("getVideoList");
        })
        .then(() => {
          dispatch("getVideo", video.videoId);
          // dispatch("getVideoList");
        })
        .catch((err) => {
          dispatch("getVideo", video.videoId);
          err;
        });
    },

    //videoId로 비디오 가져오기
    getVideo({ commit, dispatch }, videoid) {
      const API_URL = `${REST_API}/video/${videoid}`
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          commit("GET_ViDEO", res.data);
        })
        .then(() => {
          dispatch("plusVideoViewCnt",this.state.video)
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //비디오 조회수 증가
    plusVideoViewCnt({commit}, video) {
      const API_URL = `${REST_API}/video`
      axios({
        url: API_URL,
        method: "PUT",
        params: video,
      })
        .then(() => {
          commit("GET_ViDEO", this.state.video.videoId);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //비디오 리스트 가져오기
    getVideoList({ commit }) {
      const API_URL = `${REST_API}/video`
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          commit("GET_VIDEOS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //찜한 비디오 리스트 가져오기
    getLikeVideoList({commit}, userId){
      console.log("userId : " +userId)
      const API_URL = `${REST_API}/likevideo/${userId}`
      axios({
        url: API_URL,
        method: "GET",
      })
      .then((res) => {
        console.log("res.data : " +res.data)
        commit("GET_LIKEVIDEOLIST", res.data);
      
      })
      .catch((err) => {
        console.log(err);
      });
    },

    //사용자가 찜한 비디오인지 확인하기
    getLikeVideo({commit}, likevideo){
      const API_URL = `${REST_API}/likevideo`
      axios({
        url: API_URL,
        method: "GET",
        params : likevideo,
      })
      .then((res) => {
        console.log(res.status)
        commit("GET_LIKEViDEO", res.status);

        if(res.status==200) {
          commit("GET_HEART", 1)
        } else {
          commit("GET_HEART", 0)
        }
      })
      .catch((err) => {
        console.log(err);
      });
    },

    //비디오 찜하기
    addLikeVideo({ state, commit, dispatch }, likevideo) {
      const API_URL = `${REST_API}/likevideo`
      axios({
        url: API_URL,
        method: "POST",
        params: likevideo,
      })
        .then(() => {
          commit("GET_LIKEViDEO", likevideo);
          commit("GET_HEART", 1)
          
        })
        .then(() => {
          dispatch("getLikeVideoList", state.user.id);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //비디오 찜 취소하기
    deleteLikeVideo({ state, commit, dispatch }, likevideo) {
      const API_URL = `${REST_API}/likevideo/delete`
      axios({
        url: API_URL,
        method: "DELETE",
        params: likevideo,
      })
        .then(() => {
          commit("GET_HEART", 0);
        })
        .then(() => {
          dispatch("getLikeVideoList", state.user.id);
        })
        .catch((err) => {
          console.log(err);
        });
    },


    //회원가입하기
    createUser({commit}, user){
      const API_URL = `${REST_API}/user/signUp`;
     axios({
       url: API_URL,
       method: "POST",
       params: user,
     })
     .then(() => {
       commit("CREATE_USER", user);
       Swal.fire({
          position: 'center',
          icon: 'success',
          // title: '회원가입이 완료되었어요!',
          html: '<div style="font-family: SUITE-Regular;">회원가입이 완료되었어요!</div>',
          showConfirmButton: false,
          timer: 1800
      })
       router.push(`/login`);
     })
     .catch((err) => {
       alert("에러 발생");
       console.log(err);
       
     });
   },

   //로그인하기
   userLogin({state,commit, dispatch}, user){
     const API_URL = `${REST_API}/user/${user.id}`;
     axios({
       url: API_URL,
       method: "GET",
     })
     .then((res) => {
        sessionStorage.setItem("access-token", res.data["access-token"]);
        console.log("로그인에 성공한 user : " + res.data)
        commit("GET_USER", res.data);
        dispatch("getLikeVideoList", state.user.id);
        router.push("/home");
     })
     .catch((err) => {
       console.log(err);
     });
   },

   //세션에 유저 정보 보내기 
    loginSession({dispatch}, user){
     const API_URL = `${REST_API}/user/login`;
     console.log(user);

     axios({
       url: API_URL,
       method: "POST",
       params: user,
     })
     .then((res) => {
      console.log(res.status)
      if(res.status===200){
        dispatch("userLogin", user);
      } else {
         Swal.fire({
          position: 'top',
          // title: '아이디와 비밀번호를 확인해주세요',
          html: '<div style="font-family: SUITE-Regular;">아이디와 비밀번호를 확인해주세요</div>',
          showConfirmButton: false,
          timer: 1000
        })
        return;
      }

    })
    .catch((err) => {
      console.log(err);
    });
   },

   //사용자 확인하기
   getUser({commit}, id){
     const API_URL = `${REST_API}/user/${id}`;
     axios({
       url: API_URL,
       method: "GET",
     })
     .then((res) => {
       commit("GET_USER", res.data);
     })
     .catch((err) => {
       alert("에러 발생");
       console.log(err);
     });
   },

    //임시 비밀번호 받기
    getUserPass({commit, dispatch}, findPassUser){
      const API_URL = `${REST_API}/user/${findPassUser.id}`;
      axios({
        url: API_URL,
        method: "GET",
      })
      .then((res) => {
        console.log(res.data)

        if(res.data.email===findPassUser.email){
          commit("GET_TMPUSER", res.data);

          var pwd = Math.floor(Math.random() * (9999 - 1001) + 1001);
          console.log(pwd);
    
          let user = {
            id: res.data.id,
            password: pwd,
            name: res.data.name,
            email: res.data.email,
            nickname: res.data.nickname,
            profile: res.data.profile,
  
          }
          Swal.fire({
              position: 'top',
              // title: "임시 비밀번호는 " + user.password + " 입니다.",
              html: '<div style="font-family: SUITE-Regular;">임시 비밀번호는 ' + `${user.password}` +' 입니다.</div>',
          })
          dispatch("modifyPassword", user);
          
        } else {
            Swal.fire({
              position: 'top',
              // title: " 아이디 또는 이메일을 잘못 입력했어요. 입력하신 내용을 확인해주세요. ",
              html: '<div style="font-family: SUITE-Regular;"> 아이디 또는 이메일을 잘못 입력했어요. <br/> 입력하신 내용을 확인해주세요. </div>',
              showConfirmButton: false,
              timer: 1500
            })
           return;
        }
      })
      .catch((err) => {
        alert("에러 발생");
        console.log(err);
      });
    },


   //사용자 닉네임 확인하기 > 닉네임 중복확인 기능
   nickNameCheck({commit, state}, nickname){
    commit;
    const API_URL = `${REST_API}/user/check/${nickname}`;
    axios({
      url: API_URL,
      method: "GET",
    })
    .then((res) => {
      console.log(res)
      if(res.status==200){
       Swal.fire({
         position: 'top',
        //  title: nickname+" 는 이미 사용 중인 닉네임입니다",
          html: '<div style="font-family: SUITE-Regular;"> '+`${nickname}` + ' 는 이미 사용 중인 닉네임입니다</div>',
         showConfirmButton: false,
         timer: 1000
       })
        state.userNickNameCheck=0;
       } else {
         Swal.fire({
         position: 'top',
        //  title: nickname+" 는 사용 가능한 닉네임입니다",
        html: '<div style="font-family: SUITE-Regular;"> '+`${nickname}` + ' 는 사용 가능한 닉네임입니다</div>',
         showConfirmButton: false,
         timer: 1000
       })
         state.userNickNameCheck=1;
      }
    })
    .catch((err) => {
      alert("에러 발생");
      console.log(err);
    });
  },

   //사용자 아이디 확인하기 > 아이디 중복확인 기능
   idCheck({commit, state}, id){
     commit;
     const API_URL = `${REST_API}/user/${id}`;
     axios({
       url: API_URL,
       method: "GET",
     })
     .then((res) => {
       console.log(res)
       if(res.data!=''){
        Swal.fire({
          position: 'top',
          // title: id+" 는 이미 사용 중인 아이디입니다",
          html: '<div style="font-family: SUITE-Regular;"> '+`${id}` + ' 는 이미 사용 중인 아이디입니다</div>',
          showConfirmButton: false,
          timer: 1000
        })
         state.userIdCheck=0;
       } else {
         Swal.fire({
          position: 'top',
          // title: id+" 는 사용 가능한 아이디입니다",
          html: '<div style="font-family: SUITE-Regular;"> '+`${id}` + ' 는 사용 가능한 아이디입니다</div>',
          showConfirmButton: false,
          timer: 1000
        })
         state.userIdCheck=1;
       }
     })
     .catch((err) => {
       alert("에러 발생");
       console.log(err);
     });
   },

   //회원 탈퇴
   deleteUser({commit}, id){
     commit;
     console.log("회원탈퇴 메서드 : " +id)
     const API_URL = `${REST_API}/user/${id}/delete`;
     axios({
       url: API_URL,
       method: "DELETE",
     })
     .then(() => 

      Swal.fire({
        position: 'center',
        html: '<div style="font-family: SUITE-Regular;">탈퇴되었습니다</div><div>나중에 또 만나요!</div>',
        showConfirmButton: false,
        timer: 1000,
        icon: 'success',
      }))
     .catch((err) => {
       alert("에러 발생");
       console.log(err);
     })
   },


   //모든 사용자 가져오기
   getUserList({commit}){
     const API_URL = `${REST_API}/user`;
     axios({
       url: API_URL,
       method: "GET",
     })
     .then((res) => {
       console.log(res.data);
       commit("GET_USERLIST", res.data);
     })
     .catch((err) => {
       alert("에러 발생");
       console.log(err);
     });
   },

   //프로필 수정하기
   modifyProfile({commit},user){
     commit;
     const API_URL = `${REST_API}/user/modify/profile`;
     axios({
       url: API_URL,
       method: "PUT",
       params : user,
     })
     .then((res) => {
       console.log(res.data);
     })
     .catch((err) => {
       alert("에러 발생");
       console.log(err);
     });
   },

      //닉네임 수정하기
      modifyNickname({commit, dispatch},user){
        commit;
        const API_URL = `${REST_API}/user/modify/nickname`;
        axios({
          url: API_URL,
          method: "PUT",
          params : user,
        })
        .then((res) => {
          console.log(res.data);
           Swal.fire({
             position: 'top',
            //  title: '닉네임 수정완료!',
            html: '<div style="font-family: SUITE-Regular;">닉네임 수정 완료!</div>',
             showConfirmButton: false,
             timer: 1000
           })
           dispatch("getUser", user.id);
         })
        .catch((err) => {
          alert("에러 발생");
          console.log(err);
        });
      },

   //비밀번호 수정하기
   modifyPassword({commit},user){
     commit;
     const API_URL = `${REST_API}/user/modify/password`;
     axios({
       url: API_URL,
       method: "PUT",
       params : user,
     })
     .then((res) => {
       console.log(res.data);
     })
     .catch((err) => {
       alert("에러 발생");
       console.log(err);
     });
   },

   //모든 리뷰리스트 가져오기
   getReviewList({commit}){
     const API_URL = `${REST_API}/review`;
     axios({
       url: API_URL,
       method: "GET",
     })
     .then((res) => {
       console.log(res.data);
       commit("GET_REVIEWlLIST", res.data);
     })
     .catch((err) => {
       alert("에러 발생");
       console.log(err);
     });
   },

   //사용자의 리뷰리스트 가져오기
   getUserReviewList({commit}, userId){
     console.log(userId)
     const API_URL = `${REST_API}/review/${userId}`;
     axios({
       url: API_URL,
       method: "GET",
     })
     .then((res) => {
       console.log(res.data);
       commit("GET_REVIEWlLIST", res.data);
     })
     .catch((err) => {
       alert("에러 발생");
       console.log(err);
     });
   },

   //비디오의 리뷰리스트 가져오기
   getVidepReviewList({commit}, videoId){
     console.log("videoId : " +videoId)
     const API_URL = `${REST_API}/review/video/${videoId}`;
     axios({
       url: API_URL,
       method: "GET",
     })
     .then((res) => {
       console.log(res.data);
       commit("GET_REVIEWlLIST", res.data);
     })
     .catch((err) => {
       alert("리뷰리스트 에러 발생");
       console.log(err);
     });
   },

   //리뷰 등록
   createReview({commit, dispatch}, review){
     const API_URL = `${REST_API}/review/create`;
     axios({
       url: API_URL,
       method: "POST",
       params : review
     })
     .then(() => {
       commit("CREATE_REVIEW", review);
       dispatch("getVidepReviewList", review.videoId)

     })
     .catch((err) => {
       alert("리뷰 등록 에러 발생");
       console.log(err);
     });
   },
   

   //리뷰 수정
   modifyReview({commit}, review){
     commit;
     const API_URL = `${REST_API}/review/modify`;
     axios({
       url: API_URL,
       method: "PUT",
       params : review
     })
     .then((res) => {
       console.log("리뷰 수정" +res.data);
     })
     .catch((err) => {
       alert("에러 발생");
       console.log(err);
     });
   },

   //상세 비디오에서 댓글 삭제
   deleteReview({commit, dispatch}, review){
     commit;
     const API_URL = `${REST_API}/review/delete`;
     axios({
       url: API_URL,
       method: "PUT",
       params : review
     })
     .then(() => {
       dispatch("getVidepReviewList", review.videoId)
     })
     .catch((err) => {
       alert("에러 발생");
       console.log(err);
     });
   },

   //나의 댓글 모음집에서 댓글 삭제
   deleteReview2({commit, dispatch}, review){
    commit;
    const API_URL = `${REST_API}/review/delete`;
    axios({
      url: API_URL,
      method: "PUT",
      params : review
    })
    .then(() => {
     dispatch("getUserReviewList", review.userId)
    })
    .catch((err) => {
      alert("에러 발생");
      console.log(err);
    });
  },

   //리뷰 좋아요 증가
   modifyReviewCnt({commit}, review){
     commit;
     const API_URL = `${REST_API}/review`;
     axios({
       url: API_URL,
       method: "PUT",
       params : review
     })
     .then((res) => {
       console.log(res.data);
     })
     .catch((err) => {
       alert("에러 발생");
       console.log(err);
     });
   },

   //선택한 날짜의 기록 가져오기
   getCalender({commit}, calender){
      const API_URL = `${REST_API}/calender`;
      axios({
        url: API_URL,
        method: "GET",
        params : calender
      })
      .then((res) => {
        if(res.data.coment) {
          commit("GET_CALENDER", res.data);
          console.log(111)

        } else {
          commit("GET_CALENDER", "fail");
          console.log(222)
        }
      })
      .catch((err) => {
        alert("기록 가져오기 에러 발생");
        console.log(err);
      });
   },

   //기록 등록하기
   createCalender({commit, dispatch}, calender){
    commit;
    const API_URL = `${REST_API}/calender/create`;
    axios({
      url: API_URL,
      method: "POST",
      params : calender
    })
    .then((res) => {
      console.log(res.data);
      dispatch("getCalender", calender)
          Swal.fire({
          position: 'center',
          icon: 'success',
          // title: '기록 완료!',
          html: '<div style="font-family: SUITE-Regular;">기록 완료!</div>',
          showConfirmButton: false,
          timer: 1800
      })
    })
    .catch((err) => {
      alert("기록 등록하기 에러 발생");
      console.log(err);
    });
   }

  },
  modules: {
  }
})