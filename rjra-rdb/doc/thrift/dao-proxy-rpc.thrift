namespace java com.hdg.gdd.rdb.thrift
#类描述
struct Request {
  1: string requestId,
  2: string className,
  3: string methodName,
  4: list<binary> params
}

struct Response {
  1: string requestId,
  2: string resultType,
  3: string message,
  4: binary value
}

#接口描述
service DaoProxyRpc {
  Response execRequest(1:Request request);
}

#thrift-0.9.1.exe -r --gen java daoClient-proxy-rpc.thrift