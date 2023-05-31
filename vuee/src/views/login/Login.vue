<template>
  <div style="height: 100vh; overflow: hidden" >
    <div style="width: 500px; height: 400px; background-color: burlywood ; border-radius: 10px;
     margin: 150px auto; padding: 50px;">
      <div style="margin: 30px; text-align: center; font-size: 30px;
      font-width: bold; color: dodgerblue">登 录</div>

      <el-form :model="admin" :rules="rules" ref="loginFrom" >
        <el-form-item prop="username">
          <el-input v-model="admin.username" prefix-icon="el-icon-user" size="medium" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="admin.password" show-password prefix-icon="el-icon-lock" size="medium" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" size="medium" type="primary" @click="login">登录</el-button>
        </el-form-item>
      </el-form>

    </div>
  </div>
</template>

<script>

import request from "@/utils/request";
import Cookies from 'js-cookie';

export default {
  name: 'LOGIN',
  data(){
    return{
      admin:{},


      rules: {
        username: [
          {request:true, massage:'请输入用户名', trigger: 'blur'},
          {min: 3, max: 10,massage:'长度在3-10个字符', trigger: 'blur'}

        ],
        password: [
          {request:true, massage:'请输入密码', trigger: 'blur'},
          {min: 3, max: 10, massage:'长度在3-10个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods:{

    login(){


      this.$refs['loginFrom'].validate((valid) => {
        if (valid) {
          request.post('/admin/login',this.admin).then(res => {
            if (res.code === '200'){
              this.$notify.success("登录成功")



              if (res.data !== null){
                Cookies.set('admin', JSON.stringify(res.data))
              }
              this.$router.push('/')

            }else{
              this.$notify.error(res.msg)
            }
          })
        }

      })


    },


  }
}

</script>

