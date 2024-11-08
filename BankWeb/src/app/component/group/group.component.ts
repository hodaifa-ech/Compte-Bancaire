import { Component } from '@angular/core';
import { GroupService } from '../../service/servicegroup/group.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrl: './group.component.scss'
})
export class GroupComponent {
  groups: any[] = [];
  groupForm!: FormGroup;
  showAddGroupPopup: boolean = false;
  showEditGroupPopup: boolean = false;
  selectedGroupId: string | null = null;

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

  openEditGroupPopup(group: any): void {
    this.selectedGroupId = group.codeGroupe;
    this.showEditGroupPopup = true;
    this.groupForm.patchValue({
      nomGroupe: group.nomGroupe,
    });
  }

  toggleEditGroupPopup(): void {
    this.showEditGroupPopup = !this.showEditGroupPopup;
    if (!this.showEditGroupPopup) {
      this.groupForm.reset();
      this.selectedGroupId = null;
    }
  }

  updateGroup(): void {
    if (this.groupForm.valid && this.selectedGroupId) {
      const updatedGroup = { ...this.groupForm.value, codeGroupe: this.selectedGroupId };
      this.groupService.updateGroup(Number(this.selectedGroupId), updatedGroup).subscribe(() => {
        this.loadGroups();
        this.toggleEditGroupPopup();
      });
    }
  }

  confirmDeleteGroup(groupId: string): void {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You won\'t be able to revert this!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!',
    }).then((result) => {
      if (result.isConfirmed) {
        this.deleteGroup(groupId);
      }
    });
  }

  deleteGroup(groupId: string): void {
    this.groupService.deleteGroup(Number(groupId)).subscribe(() => {
      this.loadGroups();
      Swal.fire('Deleted!', 'The group has been deleted.', 'success');
    });
  }
}
