Persistent Messaging Hub
========================

About the project
-----------------

In a hub and spoke architecture, the hub is expected to be persistent and able to handle heavy loads of data. We will prepare a message store that can accept high loads of data streamed into the system (sources) and be persisted (into a track) and the same messages being returned to interested clients (sinks). A track is a persistent entity that is modeled as a page like a page in the virtual machine system.

Ideally, each page unit is 4K in size and a message the size of a page can be fitted into the system. Each page will be carved out into a buffer that can be filled by a source and can be drained by a sink. Taking this abstraction further, we will model two queues per track: a queue which is free into which a fresh set of messages can be inserted by a source and a queue of messages to be written into a sink.

Since this project is build using JVM stack, we would be leveraging the powers of NIO 2 to support direct memory buffers that will be representing a memory mapped file and have sources and sinks read and write to these buffers. We also intent to simplify the programming model by leveraging either Netty asynchronous IO model or the NIO 2 AIO features.


