import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from '../app.constants';
import { AgroChemical } from '../models/agrochemical.model';

@Injectable({
  providedIn: 'root'
})
export class AgroChemicalService {

  constructor(private readonly http: HttpClient) { }

  addAgroChemical(requestObject: AgroChemical):Observable<any> {
    return this.http.post<any>(`${apiUrl}/api/agrochemical`,requestObject);
  }

  getAgroChemicalByUserId(id:number):Observable<AgroChemical> {
    return this.http.get<AgroChemical>(`${apiUrl}/api/agrochemical/user/${id}`);
  }

  getAgroChemicalById(id:number):Observable<AgroChemical> {
    return this.http.get<AgroChemical>(`${apiUrl}/api/agrochemical/${id}`);
  }

  getAllAgroChemicals():Observable<AgroChemical[]> {
    return this.http.get<AgroChemical[]>(`${apiUrl}/api/agrochemical`);
  }

  deleteAgroChemical(id:number):Observable<void> {
    return this.http.delete<void>(`${apiUrl}/api/agrochemical/${id}`);
  }

  updateAgroChemical(id:number, requestObject:AgroChemical):Observable<AgroChemical> {
    return this.http.put<AgroChemical>(`${apiUrl}/api/agrochemical/${id}`,requestObject);
  }
}
