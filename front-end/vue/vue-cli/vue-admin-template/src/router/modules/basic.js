/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const basicRouter = {
  path: '/basic',
  component: Layout,
  redirect: '/basic/user/list',
  name: 'Basic',
  meta: { title: '系统管理', icon: 'el-icon-setting' },
  children: [
    {
      path: 'user/list',
      name: 'User',
      component: () => import('@/views/basic/userTable'),
      meta: { title: '用户管理', icon: 'el-icon-user' }
    },
    {
      path: 'userGroup/list',
      name: 'UserGroup',
      component: () => import('@/views/tree/index'),
      meta: { title: '用户组管理', icon: 'el-icon-s-custom' }
    },
    {
      path: 'userRole/list',
      name: 'UserRole',
      component: () => import('@/views/tree/index'),
      meta: { title: '角色管理', icon: 'el-icon-s-check' }
    },
    {
      path: 'company/tree',
      name: 'Company',
      component: () => import('@/views/tree/index'),
      meta: { title: '公司管理', icon: 'el-icon-office-building' }
    },
    {
      path: 'department/tree',
      name: 'Department',
      component: () => import('@/views/tree/index'),
      meta: { title: '部门管理', icon: 'el-icon-school' }
    },
    {
      path: 'area/tree',
      name: 'Area',
      component: () => import('@/views/tree/index'),
      meta: { title: '区域管理', icon: 'el-icon-location' }
    },
    {
      path: 'application/list',
      name: 'Application',
      component: () => import('@/views/tree/index'),
      meta: { title: '应用管理', icon: 'el-icon-menu' }
    },
    {
      path: 'module/tree',
      name: 'Module',
      component: () => import('@/views/tree/index'),
      meta: { title: '模块管理', icon: 'el-icon-s-grid' }
    }
  ]
}
export default basicRouter
