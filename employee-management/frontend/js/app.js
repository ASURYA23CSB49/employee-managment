const API_BASE = 'http://localhost:8080/api';

const api = {
  async request(method, endpoint, body = null) {
    const opts = {
      method,
      headers: { 'Content-Type': 'application/json' },
    };
    if (body) opts.body = JSON.stringify(body);
    const res = await fetch(`${API_BASE}${endpoint}`, opts);
    const data = await res.json();
    if (!data.success) throw new Error(data.message || 'Request failed');
    return data.data;
  },
  getEmployees: (search = '') => api.request('GET', `/employees${search ? `?search=${encodeURIComponent(search)}` : ''}`),
  getEmployee: (id) => api.request('GET', `/employees/${id}`),
  createEmployee: (data) => api.request('POST', '/employees', data),
  updateEmployee: (id, data) => api.request('PUT', `/employees/${id}`, data),
  deleteEmployee: (id) => api.request('DELETE', `/employees/${id}`),
  getDashboard: () => api.request('GET', '/employees/dashboard'),
};

// Auth helpers
const auth = {
  login(user) { localStorage.setItem('emp_user', JSON.stringify(user)); },
  logout() { localStorage.removeItem('emp_user'); window.location.href = '../index.html'; },
  getUser() {
    try { return JSON.parse(localStorage.getItem('emp_user')); }
    catch { return null; }
  },
  require() {
    if (!this.getUser()) window.location.href = '../index.html';
    return this.getUser();
  }
};

// UI helpers
function showAlert(container, message, type = 'success') {
  const icons = { success: 'check-circle', error: 'exclamation-circle', info: 'info-circle' };
  container.innerHTML = `<div class="alert alert-${type}"><i class="fas fa-${icons[type]}"></i>${message}</div>`;
  if (type === 'success') setTimeout(() => container.innerHTML = '', 3000);
}

function getDeptBadgeClass(dept) {
  const map = { Engineering: 'engineering', Marketing: 'marketing', HR: 'hr', Finance: 'finance', Operations: 'operations', Sales: 'sales' };
  return map[dept] || 'default';
}

function formatCurrency(val) {
  return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0 }).format(val);
}

function formatDate(str) {
  if (!str) return '-';
  return new Date(str).toLocaleDateString('en-US', { year: 'numeric', month: 'short', day: 'numeric' });
}

function setLoading(btn, loading) {
  btn.disabled = loading;
  btn.innerHTML = loading
    ? '<i class="fas fa-spinner fa-spin"></i> Processing...'
    : btn.dataset.original;
}

function initNavItem(page) {
  document.querySelectorAll('.nav-item[data-page]').forEach(el => {
    el.classList.toggle('active', el.dataset.page === page);
  });
}

function renderUserInfo() {
  const user = auth.getUser();
  if (user) {
    const el = document.getElementById('userDisplay');
    if (el) el.textContent = user.name;
    const av = document.getElementById('userAvatar');
    if (av) av.textContent = user.name.charAt(0).toUpperCase();
  }
}
