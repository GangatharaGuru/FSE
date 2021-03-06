import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ProjectModel } from '../models/Project';
import { AppSettings } from '../models/AppSettings';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})

export class ProjectService {

  constructor(private httpClient: HttpClient, private _userService: UserService) { }

  getAll() {
    console.log(AppSettings.ProjectsUrl+'/listProjectsDetails-modified');
    return this.httpClient.get<ProjectModel[]>(AppSettings.ProjectsUrl+'/listProjectsDetails');
  }

  getById(id: number) {
    return this.httpClient.get<ProjectModel>(AppSettings.ProjectsUrl + '/' + id);
  }

  delete(id: number) {
    const deleteUrl = AppSettings.ProjectsUrl + '/delete/' + id;
    return this.httpClient.post<any>(deleteUrl, {});
  }

  createOrUpdateProject(project) {
    return this.httpClient.post(AppSettings.ProjectsUrl+'/addProject', project);
  }

  getAllManagers() {
    return this._userService.getUsers();
  }
}
