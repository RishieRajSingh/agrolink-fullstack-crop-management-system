import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FarmermyrequestComponent } from './components/farmermyrequest/farmermyrequest.component';
import { FarmernavComponent } from './components/farmernav/farmernav.component';
import { FarmerviewchemicalComponent } from './components/farmerviewchemical/farmerviewchemical.component';
import { FarmerviewcropComponent } from './components/farmerviewcrop/farmerviewcrop.component';
import { FarmerviewfeedbackComponent } from './components/farmerviewfeedback/farmerviewfeedback.component';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SellernavComponent } from './components/sellernav/sellernav.component';
import { SellerviewchemicalComponent } from './components/sellerviewchemical/sellerviewchemical.component';
import { SellerviewfeedbackComponent } from './components/sellerviewfeedback/sellerviewfeedback.component';
import { SellerviewrequestsComponent } from './components/sellerviewrequests/sellerviewrequests.component';
import { SelleraddchemicalComponent } from './components/selleraddchemical/selleraddchemical.component';
import { FarmeraddcropComponent } from './components/farmeraddcrop/farmeraddcrop.component';
import { FarmereditcropComponent } from './components/farmereditcrop/farmereditcrop.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { FarmeraddfeedbackComponent } from './components/farmeraddfeedback/farmeraddfeedback.component';
import { LoginComponent } from './components/login/login.component';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import { SellereditchemicalComponent } from './components/sellereditchemical/sellereditchemical.component';
@NgModule({
  declarations: [
    AppComponent,
    FarmermyrequestComponent,
    FarmernavComponent,
    FarmerviewchemicalComponent,
    FarmerviewcropComponent,
    FarmerviewfeedbackComponent,
    HomeComponent,
    NavbarComponent,
    SellernavComponent,
    SellerviewchemicalComponent,
    SellerviewfeedbackComponent,
    SelleraddchemicalComponent,
    SellerviewrequestsComponent,
    FarmeraddfeedbackComponent,
    RegistrationComponent,
    FarmeraddcropComponent,
    FarmeraddfeedbackComponent,
    SelleraddchemicalComponent,
    FarmereditcropComponent,
    LoginComponent,
    SellereditchemicalComponent
],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }