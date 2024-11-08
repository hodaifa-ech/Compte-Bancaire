import { Component } from '@angular/core';
import { GroupService } from '../../service/servicegroup/group.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrl: './group.component.scss'
})
export class GroupComponent {
  groups: any[] = [];
  groupForm!: FormGroup;
  showAddGroupPopup: boolean = false;

  constructor(private groupService: GroupService, private fb: FormBuilder) {
    this.groupForm = this.fb.group({
     
      nomGroupe: ['', Validators.required],
     
    });
  }

  ngOnInit(): void {
    this.loadGroups();
  }

  loadGroups(): void {
    this.groupService.getAllGroups().subscribe(data => {
      this.groups = data;
    });
  }

  toggleAddGroupPopup(): void {
    this.showAddGroupPopup = !this.showAddGroupPopup;
    if (!this.showAddGroupPopup) {
      this.groupForm.reset();
    }
  }

  addGroup(): void {
    if (this.groupForm.valid) {
      const newGroup: any = this.groupForm.value;
      this.groupService.addGroup(newGroup).subscribe(() => {
        this.loadGroups();
        this.toggleAddGroupPopup();
      });
    }
  }
}
