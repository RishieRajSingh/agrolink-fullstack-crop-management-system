import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SelleraddchemicalComponent } from './components/selleraddchemical/selleraddchemical.component';
import { SellereditchemicalComponent } from './components/sellereditchemical/sellereditchemical.component';
import { SellerviewfeedbackComponent } from './components/sellerviewfeedback/sellerviewfeedback.component';
import { SellerviewchemicalComponent } from './components/sellerviewchemical/sellerviewchemical.component';
import { FarmeraddfeedbackComponent } from './components/farmeraddfeedback/farmeraddfeedback.component';
import { FarmerviewcropComponent } from './components/farmerviewcrop/farmerviewcrop.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { FarmeraddcropComponent } from './components/farmeraddcrop/farmeraddcrop.component';
import { HomeComponent } from './components/home/home.component';
import { SellerviewrequestsComponent } from './components/sellerviewrequests/sellerviewrequests.component';
import { FarmereditcropComponent } from './components/farmereditcrop/farmereditcrop.component';
import { FarmermyrequestComponent } from './components/farmermyrequest/farmermyrequest.component';
import { FarmerviewchemicalComponent } from './components/farmerviewchemical/farmerviewchemical.component';
import { FarmerviewfeedbackComponent } from './components/farmerviewfeedback/farmerviewfeedback.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SellernavComponent } from './components/sellernav/sellernav.component';
import { FarmernavComponent } from './components/farmernav/farmernav.component';
import { AuthGuard } from './components/authguard/auth.guard';
import { AppComponent } from './app.component';
const routes: Routes = [
  // {path:'', component: AppComponent},
  {path:'home', component: HomeComponent},
  {path:'login', component: LoginComponent},
  {path:'signup',component:RegistrationComponent},
  {path:'selleraddchemical',component:SelleraddchemicalComponent, canActivate: [AuthGuard]},
  {path:'sellereditchemical',component:SellereditchemicalComponent, canActivate: [AuthGuard]},
  {path:'sellerviewfeedback',component:SellerviewfeedbackComponent, canActivate: [AuthGuard]},
  {path:'sellerviewchemical',component:SellerviewchemicalComponent, canActivate: [AuthGuard]},
  {path:'sellerviewrequests',component:SellerviewrequestsComponent, canActivate: [AuthGuard]},
  {path:'farmeraddcrop',component:FarmeraddcropComponent, canActivate: [AuthGuard]},
  {path:'farmeraddfeedback',component:FarmeraddfeedbackComponent, canActivate: [AuthGuard]},
  {path:'farmereditcrop',component:FarmereditcropComponent, canActivate: [AuthGuard]},
  {path:'farmermyrequest',component:FarmermyrequestComponent, canActivate: [AuthGuard]},
  {path:'farmerviewchemical', component:FarmerviewchemicalComponent, canActivate: [AuthGuard]},
  {path:'farmerviewcrop', component:FarmerviewcropComponent, canActivate: [AuthGuard]},
  {path:'farmerviewfeedback', component:FarmerviewfeedbackComponent, canActivate: [AuthGuard]},
  {path:'navbar',component:NavbarComponent},
  {path:'sellernav',component:SellernavComponent},
  {path:'farmernav',component:FarmernavComponent},
  {path:'',component:HomeComponent,pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
