# 系统工具库

## 系统属性 (SystemPropertiesProxy)

### 设置值

```kotlin
set(key: String, value: String?)
```

### 获取值

```kotlin
getString(key: String, def: String? = null)
```

## 设备信息 (DeviceInfo)

### 固件版本

```kotlin
getVersion()
```

### SN号

```kotlin
getSerialNumber()
```

## 设备控制 (DeviceCtrl)

### 关机

```kotlin
shutdown()
```

### 重启

```kotlin
reboot()
```

### 恢复出厂设置

```kotlin
resetFactory()
```

## SettingsProvider（SettingsUtil）

### 设置值
```kotlin
putString(key: String, value: String?)
```
### 获取值
```kotlin
getString(key: String, defValue: String?)
```
### 注册监听
```kotlin
registerOnDataChangeListener(listener: OnDataChangeListener)
```