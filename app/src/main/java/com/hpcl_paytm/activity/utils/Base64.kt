package com.hpcl_paytm.activity.utils

object Base64 {
    fun encode(d: ByteArray?): String? {
        return if (d == null) {
            null
        } else {
            val len = d.size
            val data = ByteArray(d.size + 2)
            System.arraycopy(d, 0, data, 0, d.size)
            val dest = ByteArray(data.size / 3 * 4)
            var idx = 0
            var didx = 0
            while (idx < d.size) {
                dest[didx] = (data[idx].toInt() ushr 2 and 63).toByte()
                dest[didx + 1] =
                    (data[idx + 1].toInt() ushr 4 and 15 or (data[idx].toInt() shl 4 and 63)).toByte()
                dest[didx + 2] =
                    (data[idx + 2].toInt() ushr 6 and 3 or (data[idx + 1].toInt() shl 2 and 63)).toByte()
                dest[didx + 3] = (data[idx + 2].toInt() and 63).toByte()
                idx += 3
                didx += 4
            }
            idx = 0
            while (idx < dest.size) {
                if (dest[idx] < 26) {
                    dest[idx] = (dest[idx] + 65).toByte()
                } else if (dest[idx] < 52) {
                    dest[idx] = (dest[idx] + 97 - 26).toByte()
                } else if (dest[idx] < 62) {
                    dest[idx] = (dest[idx] + 48 - 52).toByte()
                } else if (dest[idx] < 63) {
                    dest[idx] = 43
                } else {
                    dest[idx] = 47
                }
                ++idx
            }
            idx = dest.size - 1
            while (idx > d.size * 4 / 3) {
                dest[idx] = 61
                --idx
            }
            String(dest)
        }
    }

    fun encode(s: String): String? {
        return encode(s.toByteArray())
    }

    fun decode(str: String?): ByteArray? {
        return if (str == null) {
            null
        } else {
            val data = str.toByteArray()
            decode(data)
        }
    }

    fun decode(data: ByteArray): ByteArray {
        var tail: Int
        tail = data.size
        while (data[tail - 1].toInt() == 61) {
            --tail
        }
        val dest = ByteArray(tail - data.size / 4)
        var sidx: Int
        sidx = 0
        while (sidx < data.size) {
            if (data[sidx].toInt() == 61) {
                data[sidx] = 0
            } else if (data[sidx].toInt() == 47) {
                data[sidx] = 63
            } else if (data[sidx].toInt() == 43) {
                data[sidx] = 62
            } else if (data[sidx] >= 48 && data[sidx] <= 57) {
                data[sidx] = (data[sidx] - (-4)).toByte()
            } else if (data[sidx] >= 97 && data[sidx] <= 122) {
                data[sidx] = (data[sidx] - 71).toByte()
            } else if (data[sidx] >= 65 && data[sidx] <= 90) {
                data[sidx] = (data[sidx] - 65).toByte()
            }
            ++sidx
        }
        sidx = 0
        var didx: Int
        didx = 0
        while (didx < dest.size - 2) {
            dest[didx] =
                (data[sidx].toInt() shl 2 and 255 or (data[sidx + 1].toInt() ushr 4 and 3)).toByte()
            dest[didx + 1] =
                (data[sidx + 1].toInt() shl 4 and 255 or (data[sidx + 2].toInt() ushr 2 and 15)).toByte()
            dest[didx + 2] =
                (data[sidx + 2].toInt() shl 6 and 255 or (data[sidx + 3].toInt() and 63)).toByte()
            sidx += 4
            didx += 3
        }
        if (didx < dest.size) {
            dest[didx] =
                (data[sidx].toInt() shl 2 and 255 or (data[sidx + 1].toInt() ushr 4 and 3)).toByte()
        }
        ++didx
        if (didx < dest.size) {
            dest[didx] =
                (data[sidx + 1].toInt() shl 4 and 255 or (data[sidx + 2].toInt() ushr 2 and 15)).toByte()
        }
        return dest
    }
}