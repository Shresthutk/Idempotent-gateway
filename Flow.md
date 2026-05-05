Client Request
   ↓
[ Interceptor - preHandle ]
   ↓
Check Idempotency Table
   ├── EXISTS + SUCCESS → Return stored response ❌ (stop)
   ├── EXISTS + PROCESSING → Return 409 ❌ (stop)
   └── NOT EXISTS → Insert (key, PROCESSING) ✅
   ↓
Controller
   ↓
BusinessService
   ↓
Save to Payment Table
   ↓
Return Response
   ↓
[ Interceptor - afterCompletion ]
   ↓
Update Idempotency Table (SUCCESS + response)
   ↓
Response to Client
