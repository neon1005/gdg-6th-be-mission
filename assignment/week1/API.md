UserController

1. 상품 조회  
HTTP Method: GET  
URL: /products-user?name=제품이름   
Output JSON:  
{  
   "id": 1,  
   "name": "콜라",  
   "price": 1500.0,  
   "stockQuantity": 10  
}
    

2. 상품 구매  
   HTTP Method: PATCH  
   URL: /products-user  
   Input JSON:  
[  
   {  
   "id": 1,  
   "stockQuantity": 2  
   }  
]   
   Output JSON:  
   [  
   {  
   "id": 1,  
   "name": "콜라",  
   "price": 3000.0,  
   "stockQuantity": 2  
   }  
   ]  
 
  

AdminController  
1. 상품 등록  
   HTTP Method: POST  
   URL: /products-admin  
   Input JSON:  
{  
   "name": "콜라",  
   "price": 1500.0,    
   "stockQuantity": 10  
}  
   Output JSON:  
{  
   "id": 1,  
   "name": "콜라",  
   "price": 1500.0,  
   "stockQuantity": 10  
}  
  
2. 재고 추가  
   HTTP Method: PATCH  
   URL: /products-admin/{productId}  
   Input JSON: 5  
   Output JSON:  
{  
   "id": 1,  
   "name": "콜라",  
   "price": 1500.0,  
   "stockQuantity": 15  
}  
3. 상품 삭제  
   HTTP Method: DELETE    
   URL: /products-admin  
   Input JSON:    
   [  
   {  
   "id": 1  
   }  
   ]    
   OutPut JSON:  
   [  
   {  
   "id": 2,  
   "name": "남은 제품 이름",  
   "price": 1000.0,  
   "stockQuantity": 5  
   }  
   ]  