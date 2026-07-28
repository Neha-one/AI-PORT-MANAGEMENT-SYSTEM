import { Routes, Route } from 'react-router-dom';
import Login from '../pages/Login';
import Dashboard from '../pages/Dashboard';
import AIPrediction from '../pages/AIPrediction';
import Booking from '../pages/Booking';
import Container from '../pages/Container';
import Notfound from '../pages/Notfound';
import Profile from '../pages/Profile';
import Reports from '../pages/Reports';
import Setting from '../pages/Setting';
import Ships from '../pages/Ships';
import Tracking from '../pages/Tracking';

import DashboardLayout from "../layouts/DashboardLayout";

function AppRoutes() {
  return (
    <Routes>

      <Route path='/' element={<Login />} />
      <Route path='/login' element={<Login />} />

      <Route element={<DashboardLayout />} >

        <Route path='/ai' element={<AIPrediction />} />
        <Route path='/booking' element={<Booking />} />
        <Route path='/container' element={<Container />} />
        <Route path='/dashboard' element={<Dashboard />} />
        <Route path='/profile' element={<Profile />} />
        <Route path='/reports' element={<Reports />} />
        <Route path='/setting' element={<Setting />} />
        <Route path='/ships' element={<Ships />} />
        <Route path='/tracking' element={<Tracking />} />

      </Route >

      <Route path='*' element={<Notfound />} />

    </Routes>
  )
}
export default AppRoutes;