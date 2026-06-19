function renderLayout(pageTitle, activePage) {
  const user = auth.require();
  return `
    <div class="layout">
      <aside class="sidebar" id="sidebar">
        <div class="sidebar-header">
          <i class="fas fa-users-cog"></i>
          <div>
            <h1>EMS</h1>
            <span>Employee Management</span>
          </div>
        </div>
        <nav class="sidebar-nav">
          <a href="dashboard.html" class="nav-item ${activePage==='dashboard'?'active':''}" data-page="dashboard">
            <i class="fas fa-tachometer-alt"></i> Dashboard
          </a>
          <a href="employees.html" class="nav-item ${activePage==='employees'?'active':''}" data-page="employees">
            <i class="fas fa-users"></i> Employee List
          </a>
          <a href="add-employee.html" class="nav-item ${activePage==='add'?'active':''}" data-page="add">
            <i class="fas fa-user-plus"></i> Add Employee
          </a>
        </nav>
        <div class="sidebar-footer">
          <button class="nav-item" onclick="auth.logout()" style="color:#f87171">
            <i class="fas fa-sign-out-alt"></i> Logout
          </button>
        </div>
      </aside>
      <div class="main-content">
        <header class="topbar">
          <div class="topbar-left">
            <button class="btn btn-outline btn-sm" id="menuToggle" onclick="document.getElementById('sidebar').classList.toggle('open')" style="display:none">
              <i class="fas fa-bars"></i>
            </button>
            <div>
              <h2>${pageTitle}</h2>
            </div>
          </div>
          <div class="topbar-right">
            <div class="user-info">
              <div class="avatar" id="userAvatar">${user.name.charAt(0).toUpperCase()}</div>
              <span id="userDisplay">${user.name}</span>
            </div>
          </div>
        </header>
        <main class="page-content" id="pageContent">`;
}

function closeLayout() {
  return `</main></div></div>`;
}

// Check mobile and show menu toggle
window.addEventListener('load', () => {
  const toggle = document.getElementById('menuToggle');
  if (toggle && window.innerWidth <= 768) toggle.style.display = '';
});
