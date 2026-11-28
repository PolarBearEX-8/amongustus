#version 120

uniform float offset;
uniform sampler2D tex;
uniform vec2 resolution;

void main() {
    vec4 color = texture2D(tex, (gl_FragCoord.xy + vec2(-1.0, 0.0) * offset) / resolution);
    color += texture2D(tex, (gl_FragCoord.xy + vec2(-0.5, 0.5) * offset) / resolution) * 2.0;
    color += texture2D(tex, (gl_FragCoord.xy + vec2(0.0, 1.0) * offset) / resolution);
    color += texture2D(tex, (gl_FragCoord.xy + vec2(0.5, 0.5) * offset) / resolution) * 2.0;
    color += texture2D(tex, (gl_FragCoord.xy + vec2(1.0, 0.0) * offset) / resolution);
    color += texture2D(tex, (gl_FragCoord.xy + vec2(0.5, -0.5) * offset) / resolution) * 2.0;
    color += texture2D(tex, (gl_FragCoord.xy + vec2(0.0, -1.0) * offset) / resolution);
    color += texture2D(tex, (gl_FragCoord.xy + vec2(-0.5, -0.5) * offset) / resolution) * 2.0;

    color /= 12.0;
    color.a = 1.0;
    gl_FragColor = color;
}